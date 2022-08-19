package com.coup.model;

import java.util.List;

public interface I_CoupDAO {
	public void insert(CoupVO coupVO);
    public void update(CoupVO coupVO);
//    public void delete(Integer coupVO);
    public CoupVO findByPrimaryKey(Integer coup_no);
    public List<CoupVO> getAll();
}