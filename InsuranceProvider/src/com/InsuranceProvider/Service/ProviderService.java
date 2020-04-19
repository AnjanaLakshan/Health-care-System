package com.InsuranceProvider.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.InsuranceProvider.DBConnector.Connector;
import com.InsuranceProvider.Model.Provider;


public class ProviderService {

	Connector con = Connector.getInstance();


	private static final ProviderService obj = new ProviderService();

	public static ProviderService getInstance() {
		return obj;
	}

//	public void save(Provider obj) throws Exception {
//		con.getConnection();
//		con.aud("INSERT INTO provider(name,nic,income,level,claim) VALUES ('" + obj.getName()+ "', " + "'"
//				+ obj.getNic() + "', '" + obj.getIncome() + "', '" + obj.getLevel() + ',"+obj.getClaim() +"');
//	}

//	public void update(AppointModel obj) throws Exception {
//		con.getConnection();
//		con.aud("UPDATE appoint SET doc_id = '" + obj.getDoc_id() + "', PatientID = '" + obj.getPatientID()
//				+ "', date = '" + obj.getDate() + "'," + "time = '" + obj.getTime() + "' " + "WHERE appoint_id='"
//				+ obj.getAppoint_id() + "'");
//	}
//
//	public void delete(AppointModel obj) throws Exception {
//		con.getConnection();
//		con.aud("DELETE FROM appoint WHERE appoint_id='" + obj.getAppoint_id() + "'");
//	}

	public List<Provider> getAll() throws Exception {
		List<Provider> list = new ArrayList<Provider>();
		con.getConnection();
		ResultSet rset = con.srh("SELECT * FROM provider");
		while (rset.next()) {
			Provider obj = new Provider();
			obj.setId(rset.getInt(1));
			obj.setName(rset.getString(2));
			obj.setNic(rset.getString(3));
			obj.setIncome(rset.getString(4));
			obj.setLevel(rset.getString(5));
			obj.setClaim(rset.getString(6));

			list.add(obj);
		}
		return list;
	}

//	public AppointModel search(int appoint_id) throws Exception {
//		con.getConnection();
//		AppointModel obj = null;
//		ResultSet rset = con.srh("SELECT * FROM appoint WHERE Stock_id='" + appoint_id + "'");
//		while (rset.next()) {
//			obj = new AppointModel();
//			obj.setAppoint_id(rset.getInt(1));
//			obj.setDoc_id(rset.getInt(2));
//			obj.setPatientID(rset.getInt(3));
//			obj.setDate(rset.getString(4));
//			obj.setTime(rset.getString(5));
//
//		}
//		return obj;
//	}

}