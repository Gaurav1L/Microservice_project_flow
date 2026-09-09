package com.order_service;

import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.mysql.MySQLContainer;

import static java.lang.Math.log;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class OrderServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mySQLContainer.start();
	}

	@Test
	void shouldSubmitOrder() {
		String submitOrderJson = """
                {
                     "skuCode": "HP Laptop",
                     "price": 100000,
                     "quantity": 1
                }
                """;

		// Semi-colon ko aakhri line me lagaya hai extract ke baad
		var responseBodyString = RestAssured.given()
				.contentType("application/json")
				.body(submitOrderJson)
				.when()
				.post("/api/order")
				.then()
				.log().all() // Isse pura visual JSON output console par print hoga
				.statusCode(201)
				.body("skuCode", org.hamcrest.Matchers.equalTo("HP Laptop"))
				.body("price", org.hamcrest.Matchers.equalTo(100000)) // ❌ Yahan se semi-colon hata diya
				.extract()
				.body().asString();

		log.info("Response received inside test: {}", responseBodyString);
	}
}
