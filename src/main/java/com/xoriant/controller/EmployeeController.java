package com.xoriant.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.xoriant.entity.EmployeeEntity;
import com.xoriant.pojo.EmployeePOJO;
import com.xoriant.service.EmployeeService;
import com.xoriant.util.EmployeePdfExporterUtil;

@RestController
@RequestMapping()
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/addemployee")
	public ResponseEntity<?> addEmployee(@RequestBody EmployeePOJO employee) {
		return new ResponseEntity<>(employeeService.adduser(employee), HttpStatus.CREATED);
	}

	@GetMapping("/listemployee")
	public ResponseEntity<?> getDataList() {
		return new ResponseEntity<>(employeeService.getUsers(), HttpStatus.OK);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		employeeService.deleteById(id);
		return new ResponseEntity<>("Employee Deleted successfully", HttpStatus.ACCEPTED);

	}

	@PutMapping("update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody EmployeePOJO emp) {
		return new ResponseEntity<>(employeeService.updateEmployee(id, emp), HttpStatus.OK);
	}

	@GetMapping("pagablelist/{page}")
	public ResponseEntity<?> pagablelist(@PathVariable("page") int page) {
		Pageable of = PageRequest.of(page, 6);
		Page<EmployeeEntity> empPageList = employeeService.getpagelist(of);
		return new ResponseEntity<>(empPageList, HttpStatus.OK);

	}

	@GetMapping("search/{searchterm}")
	public ResponseEntity<?> searchQuery(@PathVariable("searchterm") String searchterm) {
		System.out.println(searchterm);
		List<EmployeeEntity> searchList = employeeService.searchEmployee(searchterm);
		if (searchList.size() > 0)
			return new ResponseEntity<>(searchList, HttpStatus.ACCEPTED);
		else {

			return new ResponseEntity<>("Employee Not Found", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("likesearch/{searchterm}")
	public ResponseEntity<?> searchLike(@PathVariable("searchterm") String searchTerm) {
		List<EmployeeEntity> searchList = employeeService.searchEmployeeLike(searchTerm);
		if (searchList.size() > 0) {
			return new ResponseEntity<>(searchList, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Employee Not Found", HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("exportpdf/{page}")
	public void exportToPDF(@PathVariable("page") int page, HttpServletResponse response)
			throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Employees_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		Pageable of = PageRequest.of(page, 6);
		Page<EmployeeEntity> empPageList = employeeService.getpagelist(of);
		List<EmployeeEntity> result = empPageList.getContent();
		EmployeePdfExporterUtil exporter = new EmployeePdfExporterUtil(result);
		exporter.export(response);

	}
}
