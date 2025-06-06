package com.example.medical_helps.utils;


import com.example.medical_helps.Helper.DataHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形工具类
 * @author chengguangrong@live.cn
 */
public class TreeUtils {

    /**
     * 列表转树形列表
     * @param sourceList
     * @return
     */
    public static List<DataHelper> listToTree(List<DataHelper> sourceList){
        List<DataHelper> listTree = new ArrayList<DataHelper>();
        for(DataHelper node:sourceList){
            if (node.get("parentId")==null || "".equals(node.get("parentId"))){
                listTree.add(setChild(node,sourceList));
            }
        }
        return listTree;
    }

    private static DataHelper setChild(DataHelper parent, List<DataHelper> sourceList){
        List<DataHelper> childList = new ArrayList<DataHelper>();
        for (DataHelper subNode:sourceList){
            if(parent.get("id").toString().equals(subNode.get("parentId"))){
                childList.add(setChild(subNode,sourceList));
            }
        }
        if (childList.size()>0) {
            parent.put("children", childList);
        }
        return parent;
    }

}
