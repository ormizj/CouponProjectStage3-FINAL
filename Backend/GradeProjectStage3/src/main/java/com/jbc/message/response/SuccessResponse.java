package com.jbc.message.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * {@code class} used for {@code String} type responses.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 */
@Getter
@Setter
@AllArgsConstructor
@ApiModel(description = "String wrapper class, for successful responses.")
public class SuccessResponse {

	/* attributes */
	@ApiModelProperty(value = "Success message.")
	private String response;

}