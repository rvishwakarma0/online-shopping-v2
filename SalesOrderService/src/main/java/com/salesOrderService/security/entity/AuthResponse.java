package com.salesOrderService.security.entity;
/**
 * 
 * This is a model class for authenticated response 
 * @author POD-3
 *
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthResponse {

	private String uid;

	private String name;

	private boolean valid;

	
}
