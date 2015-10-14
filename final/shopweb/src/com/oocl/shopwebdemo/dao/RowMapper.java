package com.oocl.shopwebdemo.dao;

import java.sql.*;
import java.util.*;

public interface RowMapper<T> {
	public List<T> getRowMapper(ResultSet rs) throws Exception;
}
