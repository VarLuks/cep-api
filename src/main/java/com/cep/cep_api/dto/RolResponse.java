package com.cep.cep_api.dto;
/*Para devolver Response, y para pedir (por ejemplo el body) es Request
* Por ejemplo para crear un rol con paso de json como atributo; CreateRolRequest
* Y por ejemplo si quiero hacer un update debo de crear un record por ejemplo UpdateRolRequest
* Estos son los datos que quiero mostrar*/
import com.cep.cep_api.domain.Rol;

import java.util.Date;

public record RolResponse(Long id, String description, Date created_at, Date modified_at) { }

