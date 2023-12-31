package com.spring.peluqueria.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the productos database table.
 * 
 */
@Entity
@Table(name="productos")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_producto")
	private Long idProducto;

	private String descripcion;

	@Lob
	private String foto;

	private String nombre;

	private BigDecimal precio;

	private int stock;

	//bi-directional many-to-one association to Productosgrupo
	@ManyToOne
	@JoinColumn(name="id_productogrupo")
	private ProductGroup productosgrupo;

	public Product() {
	}

	public Long getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public ProductGroup getProductosgrupo() {
		return this.productosgrupo;
	}

	public void setProductosgrupo(ProductGroup productosgrupo) {
		this.productosgrupo = productosgrupo;
	}

}