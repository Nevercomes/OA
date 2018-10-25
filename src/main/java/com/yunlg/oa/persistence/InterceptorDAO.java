package com.yunlg.oa.persistence;

import javax.persistence.PersistenceException;
import java.util.List;

public interface InterceptorDAO {

    List<String> getAuthCodeList(String userId) throws PersistenceException;

    List<Integer> getAuthDepNum(String authCode) throws PersistenceException;
}
