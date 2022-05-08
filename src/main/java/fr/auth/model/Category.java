package fr.auth.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	private String name;
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL ,orphanRemoval = true,fetch = FetchType.EAGER)
	private List<Product> products;

}
