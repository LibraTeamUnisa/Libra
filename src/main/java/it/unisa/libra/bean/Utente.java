package it.unisa.libra.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Utente 
{
	@Id
	private String email;
	private String password;
	private String telefono;
	private String indirizzo;
	private String imgPath;
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email=email;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password=password;
	}
	
	public String getTelefono()
	{
		return telefono;
	}
	
	public void setTelefono(String telefono)
	{
		this.telefono=telefono;
	}
	
	public String getIndirizzo()
	{
		return indirizzo;
	}
	
	public void setIndirizzo(String indirizzo)
	{
		this.indirizzo=indirizzo;
	}
	
	public String getImagePath()
	{
		return imgPath;
	}
	
	public void setImagePath(String imgPath)
	{
		this.imgPath=imgPath;
	}
	
}
