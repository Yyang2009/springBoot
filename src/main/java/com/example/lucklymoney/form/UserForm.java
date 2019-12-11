package com.example.lucklymoney.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhusidao855
 * @date 2019/12/6
 */
@Data
@ApiModel
public class UserForm {

    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("密码")
    private String passWord;

}
