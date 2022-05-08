package fr.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


/**
 * 
 * @author hicham
 *
 */

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@NotBlank
	private String name;
	private double price;
    @ManyToOne
    @JoinColumn(name = "category_id")
	private Category category;

}
