package fr.auth.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * @author hicham
 *
 */

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
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
