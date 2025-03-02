package com.example.samuraitravel.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ReviewEditForm {
	@NotNull
	private Integer id;
	
	@NotBlank(message = "氏名を入力してください。")
	private String name;
	
	@NotNull(message = "評価を入力してください。")
	@Min(value = 0, message = "評価は0以上に設定してください。")
	private Integer rating; 
	
	@NotNull(message = "評価内容を入力してください。")
	private String content;
}
