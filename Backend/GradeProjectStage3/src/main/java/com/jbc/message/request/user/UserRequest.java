package com.jbc.message.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * {@link com.jbc.model.user.User} type {@code class} used for
 * {@code Controller} requests.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see user#User
 */
@Setter
@Getter
@ApiModel(value = "Admin", description = "Email between users must be unique.\n\nManages Companies.\nManages Customers\nManages Logs.")
public class UserRequest {

	/* attributes */
	@ApiModelProperty(value = "Auto-incremented.", example = "0")
	protected long id;
	@ApiModelProperty(example = "email@email.com", value = "Min=1, Max=255 (spaces gets removed).\nMust be in email format.\nGets lower-cased.")
	protected String email;
	@ApiModelProperty(value = "Min=1, Max=255.")
	protected String password;

}
