package com.joaoldantasn.vendas.validation;

import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@SuppressWarnings("rawtypes")
public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List>{

	@Override
	public boolean isValid(List list, ConstraintValidatorContext context) {
		
		return list != null && !list.isEmpty();
	}
	
	@Override
	public void initialize(NotEmptyList constraintAnnotation) {
		
	}

}
