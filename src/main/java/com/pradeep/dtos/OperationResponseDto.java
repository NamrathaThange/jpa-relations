package com.pradeep.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OperationResponseDto {
	private boolean status;
	private String message;
}
