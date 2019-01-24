package es.in2.dsdibaapi.controller;

import org.springframework.beans.factory.annotation.Value;

public abstract class BaseController {
	
	@Value("${error.notfound}")
    private String errorNotFodund;
	
	public String getErrorNotFound (Class c, Object id) {
		return c.getSimpleName()+"("+id.toString()+") "+errorNotFodund;
	}
}
