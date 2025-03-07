package com.example.samuraitravel.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewRegisterForm {
	private Integer houseId;
	
	@NotNull(message = "評価を入力してください。")
	@Min(value = 0, message = "評価は0以上に設定してください。")
	private Integer rating;
	
	@NotBlank(message = "評価内容を入力してください。")
	private String content;
}
