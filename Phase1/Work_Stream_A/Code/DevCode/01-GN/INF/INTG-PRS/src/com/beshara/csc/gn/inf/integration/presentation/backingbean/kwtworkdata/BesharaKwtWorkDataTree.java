package com.beshara.csc.gn.inf.integration.presentation.backingbean.kwtworkdata;


import com.beshara.base.dto.ITreeDTO;
import com.beshara.csc.inf.business.dto.IKwtWorkDataTreeDTO;
import com.beshara.csc.inf.business.dto.KwtWorkDataTreeDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;

import java.rmi.RemoteException;

import java.util.List;

import org.apache.myfaces.custom.tree2.TreeNodeBase;


public class BesharaKwtWorkDataTree extends TreeNodeBase implements java.io.Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    Long id;
    String treeId;
    String detailId;
    String treeParentId;
    Long parentId;
    boolean hasChild;
    boolean booleanLeaf;

    String treeNodeLevelsID;

    private BesharaKwtWorkDataTree parent;
    private boolean expanded;

    private long childrenCount;

    private String fullPathNodeName;

    private String privateCode; //used to store privateCode of DTOs that has privateCode
    private TreeNodeBase treeNodeBase = new TreeNodeBase();
    private IKwtWorkDataTreeDTO kwtWorkDataTreeDTO = new KwtWorkDataTreeDTO();
    /*
     * a new variable treeLevelId
     * to store Tree Level for "each" node
     * default value "0"
     * used to set style class according to level for multi colored levels in some trees
     * By H.Ahmed
     * Nov. 2013
     */

    private int treeLevelId = 0;

    public BesharaKwtWorkDataTree() {
    }

    public BesharaKwtWorkDataTree(BesharaKwtWorkDataTree parent) {
        this.parent = parent;
    }

    public BesharaKwtWorkDataTree(String s1, String s2, String treeId, String detailId, String treeParentId,
                                  boolean hasChild, boolean booleanLeaf, boolean b1, int treeLevelId,
                                  IKwtWorkDataTreeDTO kwtWorkDataTreeDTO) {
        super(s1, s2, b1);
        this.setTreeId(treeId);
        this.setDetailId(detailId);
        this.setTreeParentId(treeParentId);
        this.setIdentifier(s2);
        this.setHasChild(hasChild);
        this.setBooleanLeaf(booleanLeaf);
        this.setTreeLevelId(treeLevelId);
        this.setKwtWorkDataTreeDTO(kwtWorkDataTreeDTO);
    }

    public BesharaKwtWorkDataTree(String s1, String s2, String treeId, String detailId, String treeParentId,
                                  boolean hasChild, boolean booleanLeaf, boolean b1, String treeNodeLevel,
                                  IKwtWorkDataTreeDTO kwtWorkDataTreeDTO) {
        super(s1, s2, b1);
        this.setTreeId(treeId);
        this.setDetailId(detailId);
        this.setTreeParentId(treeParentId);
        this.setIdentifier(s2);
        this.setHasChild(hasChild);
        this.setBooleanLeaf(booleanLeaf);
        this.setTreeNodeLevelsID(treeNodeLevel);
    }


    public int getIndex() {
        return parent == null ? 0 : getParent().getChildren().indexOf(this);
    }

    public String getPath() {
        String nodeIndex = "" + getIndex();
        StringBuilder sb = new StringBuilder(nodeIndex);
        for (BesharaKwtWorkDataTree n = getParent(); n != null; n = n.getParent()) {
            sb.insert(0, ':').insert(0, n.getIndex());
        }
        return sb.toString();
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setBooleanLeaf(boolean booleanLeaf) {
        this.booleanLeaf = booleanLeaf;
    }

    public boolean isBooleanLeaf() {
        return booleanLeaf;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

    public String getTreeId() {
        return treeId;
    }

    public void setTreeParentId(String treeParentId) {
        this.treeParentId = treeParentId;
    }

    public String getTreeParentId() {
        return treeParentId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setTreeNodeLevelsID(String treeNodeLevelsID) {
        this.treeNodeLevelsID = treeNodeLevelsID;
    }

    public String getTreeNodeLevelsID() {
        return treeNodeLevelsID;
    }

    public void setParent(BesharaKwtWorkDataTree parent) {
        this.parent = parent;
    }

    public BesharaKwtWorkDataTree getParent() {
        return parent;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setChildrenCount(long childrenCount) {
        this.childrenCount = childrenCount;
    }

    public long getChildrenCount() {
        return childrenCount;
    }

    public void setFullPathNodeName(String fullPathNodeName) {
        this.fullPathNodeName = fullPathNodeName;
    }

    public String getFullPathNodeName() {
        return fullPathNodeName;
    }

    public void setPrivateCode(String privateCode) {
        this.privateCode = privateCode;
    }

    public String getPrivateCode() {
        return privateCode;
    }

    public void setTreeLevelId(int treeLevelId) {
        this.treeLevelId = treeLevelId;
    }

    public int getTreeLevelId() {
        return treeLevelId;
    }


    public BesharaKwtWorkDataTree parseTree(List treeList, ITreeDTO treeDTO,
                                            BesharaKwtWorkDataTree treeNodeBase) throws DataBaseException,
                                                                                        RemoteException {

        String treeLevel = treeNodeBase.getTreeNodeLevelsID();
        for (int i = 0; i < treeList.size(); i++) {

            String tempTreeLevel = treeLevel;
            if (treeNodeBase.getChildCount() > 0) {
                tempTreeLevel = treeLevel + ":" + i;
            }

            ITreeDTO treeDTOElem = (ITreeDTO)treeList.get(i);

            if (treeDTOElem.getParentCode() != null &&
                (treeDTOElem.getParentCode().getKey().equals(treeDTO.getCode().getKey()))) {
                if (treeDTOElem.getChildernNumbers() != 0) {
                    hasChild = true;
                } else {
                    hasChild = false;
                }
                BesharaKwtWorkDataTree treeNode = null;
                treeNode =
                        new BesharaKwtWorkDataTree("foo-folder", treeDTOElem.getName(), treeDTOElem.getCode().getKeys()[0].toString(),
                                                   treeDTOElem.getCode().getKey().toString(),
                                                   (String)treeDTOElem.getParentCode().getKey(), hasChild,
                                                   treeDTOElem.isBooleanLeafFlag(), treeDTOElem.isBooleanLeafFlag(),
                                                   tempTreeLevel, (IKwtWorkDataTreeDTO)treeDTOElem);

                if (treeNode != null) {
                    treeNodeBase.getChildren().add(treeNode);
                    if (treeDTOElem.getChildernNumbers() != 0) {
                        parseTree(treeDTOElem.getChildrenList(), treeDTOElem, treeNode);
                    }
                }
            }


        }
        return treeNodeBase;
    }

    public void setTreeNodeBase(TreeNodeBase treeNodeBase) {
        this.treeNodeBase = treeNodeBase;
    }

    public TreeNodeBase getTreeNodeBase() throws DataBaseException, RemoteException, Exception {
        return treeNodeBase;
    }

    public void setKwtWorkDataTreeDTO(IKwtWorkDataTreeDTO kwtWorkDataTreeDTO) {
        this.kwtWorkDataTreeDTO = kwtWorkDataTreeDTO;
    }

    public IKwtWorkDataTreeDTO getKwtWorkDataTreeDTO() {
        return kwtWorkDataTreeDTO;
    }
}
