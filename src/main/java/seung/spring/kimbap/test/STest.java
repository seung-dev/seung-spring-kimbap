package seung.spring.kimbap.test;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@DynamicUpdate
@Table(name = "t_test_jpa")
@Entity
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class STest {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@NotBlank
	@Column
	private String col01;
	
	@Column
	private String col02;
	
	@Column
	private Date date_c;
	
	@Column
	private Date date_u;
	
	protected STest() {}
	
}
