package co.edu.icesi.fi.tics.tssc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the TSSC_ADMIN database table.
 * 
 */
@Entity
@Table(name = "TSSC_ADMIN")
@NamedQuery(name = "TsscAdmin.findAll", query = "SELECT t FROM TsscAdmin t")
public class TsscAdmin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TSSC_ADMIN_ID_GENERATOR", allocationSize = 1, sequenceName = "TSSC_ADMIN_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TSSC_ADMIN_ID_GENERATOR")
	private long id;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "TYPE")
	private AdminType type;

	@Column(name = "AD_USER")
	private String user;

	// bi-directional many-to-one association to TsscState
	@ManyToOne
	@JoinColumn(name = "TSSC_STATE_ID")
	private TsscState tsscState;

	// bi-directional many-to-one association to TsscGameAdmin
	@OneToMany(mappedBy = "tsscAdmin")
	private List<TsscGameAdmin> tsscGameAdmins;

	public TsscAdmin() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AdminType getType() {
		return this.type;
	}

	public void setType(AdminType type) {
		this.type = type;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public TsscState getTsscState() {
		return this.tsscState;
	}

	public void setTsscState(TsscState tsscState) {
		this.tsscState = tsscState;
	}

	public List<TsscGameAdmin> getTsscGameAdmins() {
		return this.tsscGameAdmins;
	}

	public void setTsscGameAdmins(List<TsscGameAdmin> tsscGameAdmins) {
		this.tsscGameAdmins = tsscGameAdmins;
	}

	public TsscGameAdmin addTsscGameAdmin(TsscGameAdmin tsscGameAdmin) {
		getTsscGameAdmins().add(tsscGameAdmin);
		tsscGameAdmin.setTsscAdmin(this);

		return tsscGameAdmin;
	}

	public TsscGameAdmin removeTsscGameAdmin(TsscGameAdmin tsscGameAdmin) {
		getTsscGameAdmins().remove(tsscGameAdmin);
		tsscGameAdmin.setTsscAdmin(null);

		return tsscGameAdmin;
	}

}