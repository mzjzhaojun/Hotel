package com.yichuang.kyjd.rest.service.dictionaries;

import java.util.List;
import com.yichuang.kyjd.rest.entity.dictionaries.Dictionaries;

/**
* @author zj    default  
* 
* @version 1.1
*/

public interface IDictionariesService {
	public List<Dictionaries> selectDictionaries(Dictionaries t);
}