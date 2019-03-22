package es.in2.dsdibaapi.json;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class UserJson implements Serializable {

	private String username;
	private String password;
}
