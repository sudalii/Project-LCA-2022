package org.openlca.core.database;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openlca.core.model.Location;
import org.openlca.core.model.descriptors.LocationDescriptor;

public class LocationDao
		extends RootEntityDao<Location, LocationDescriptor> {

	public LocationDao(IDatabase db) {
		super(Location.class, LocationDescriptor.class, db);
	}

	@Override
	protected String[] getDescriptorFields() {
		return new String[]{
				"id",
				"ref_id",
				"name",
				"description",
				"version",
				"last_change",
				"f_category",
				"library",
				"tags",
				"code",
		};
	}

	@Override
	protected LocationDescriptor createDescriptor(Object[] record) {
		if (record == null)
			return null;
		var d = super.createDescriptor(record);
		d.code = (String) record[9];
		return d;
	}

	/**
	 * Get the location codes from the database in a map: location id -> location
	 * code.
	 */
	public Map<Long, String> getCodes() {
		if (db == null)
			return Collections.emptyMap();
		String sql = "select id, code from tbl_locations";
		Map<Long, String> map = new HashMap<>();
		try {
			NativeSql.on(db).query(sql, r -> {
				map.put(r.getLong(1), r.getString(2));
				return true;
			});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return map;
	}

}
