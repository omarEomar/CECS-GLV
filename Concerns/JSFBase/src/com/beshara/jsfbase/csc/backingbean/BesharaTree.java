package com.beshara.jsfbase.csc.backingbean;


import org.apache.myfaces.custom.tree2.TreeNodeBase;


/**
 *this is a custom bean for TreeNodeBase
 *
 */
public class BesharaTree extends TreeNodeBase implements java.io.Serializable {

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

    private BesharaTree parent;
    private boolean expanded;

    private long childrenCount;

    private String fullPathNodeName;

    private String privateCode; //used to store privateCode of DTOs that has privateCode

    /*
     * a new variable treeLevelId
     * to store Tree Level for "each" node 
     * default value "0"
     * used to set style class according to level for multi colored levels in some trees
     * By H.Ahmed
     * Nov. 2013
     */
    
    private int treeLevelId=0;
    
    public BesharaTree() {
    }

    public BesharaTree(BesharaTree parent) {
        this.parent = parent;
    }

    public BesharaTree(String s1, String s2, Long id, Long parentId, 
                       boolean booleanLeaf, boolean b1) {
        super(s1, s2, b1);
        this.setId(id);
        this.setParentId(parentId);
        this.setIdentifier(s2);
        this.setBooleanLeaf(booleanLeaf);
    }


    public BesharaTree(String s1, String s2, Long id, Long parentId, 
                       boolean hasChild, boolean booleanLeaf, boolean b1) {
        super(s1, s2, b1);
        this.setId(id);
        this.setParentId(parentId);
        this.setIdentifier(s2);
        this.setHasChild(hasChild);
        this.setBooleanLeaf(booleanLeaf);
    }

    /**
     * this is the Constructor using
     * @param s1
     * @param s2
     * @param treeId
     * @param treeParentId
     * @param hasChild
     * @param booleanLeaf
     * @param b1
     */
    public BesharaTree(String s1, String s2, String treeId, 
                       String treeParentId, boolean hasChild, 
                       boolean booleanLeaf, boolean b1) {
        super(s1, s2, b1);
        this.setTreeId(treeId);
        this.setTreeParentId(treeParentId);
        this.setIdentifier(s2);
        this.setHasChild(hasChild);
        this.setBooleanLeaf(booleanLeaf);
    }
    /*
     * add new Constructor
     * to store treeLevel for each node
     * By H.Ahmed
     * Nov. 2013
     */
    public BesharaTree(String s1, String s2, String treeId, 
                       String treeParentId, boolean hasChild, 
                       boolean booleanLeaf, boolean b1,int treeLevelId ) {
        super(s1, s2, b1);
        this.setTreeId(treeId);
        this.setTreeParentId(treeParentId);
        this.setIdentifier(s2);
        this.setHasChild(hasChild);
        this.setBooleanLeaf(booleanLeaf);
        this.setTreeLevelId(treeLevelId);
    }

    public BesharaTree(String s1, String s2, String treeId, 
                       String treeParentId, boolean hasChild, 
                       boolean booleanLeaf, boolean b1, String treeNodeLevel) {
        super(s1, s2, b1);
        this.setTreeId(treeId);
        this.setTreeParentId(treeParentId);
        this.setIdentifier(s2);
        this.setHasChild(hasChild);
        this.setBooleanLeaf(booleanLeaf);
        this.setTreeNodeLevelsID(treeNodeLevel);
    }

    /*
     * add new Constructor
     * to store treeLevel for each node
     * By H.Ahmed
     * Nov. 2013
     */
    public BesharaTree(String s1, String s2, String treeId, 
                       String treeParentId, boolean hasChild, 
                       boolean booleanLeaf, boolean b1, String treeNodeLevel,int treeLevelId) {
        super(s1, s2, b1);
        this.setTreeId(treeId);
        this.setTreeParentId(treeParentId);
        this.setIdentifier(s2);
        this.setHasChild(hasChild);
        this.setBooleanLeaf(booleanLeaf);
        this.setTreeNodeLevelsID(treeNodeLevel);
        this.setTreeLevelId(treeLevelId);
    }

    /**
     * used to store privateCode of DTOs that has privateCode
     */

    public BesharaTree(String s1, String s2, String treeId, 
                       String treeParentId, boolean hasChild, 
                       String privateCode, boolean booleanLeaf, boolean b1, 
                       String treeNodeLevel) {
        super(s1, s2, b1);
        this.setTreeId(treeId);
        this.setTreeParentId(treeParentId);
        this.setIdentifier(s2);
        this.setHasChild(hasChild);
        this.setPrivateCode(privateCode);
        this.setBooleanLeaf(booleanLeaf);
        this.setTreeNodeLevelsID(treeNodeLevel);
    }

        /*
         * add new Constructor
         * to store treeLevel for each node
         * By H.Ahmed
         * Nov. 2013
         */

    public BesharaTree(String s1, String s2, String treeId, 
                       String treeParentId, boolean hasChild, 
                       String privateCode, boolean booleanLeaf, boolean b1, 
                       String treeNodeLevel,int treeLevelId) {
        super(s1, s2, b1);
        this.setTreeId(treeId);
        this.setTreeParentId(treeParentId);
        this.setIdentifier(s2);
        this.setHasChild(hasChild);
        this.setPrivateCode(privateCode);
        this.setBooleanLeaf(booleanLeaf);
        this.setTreeNodeLevelsID(treeNodeLevel);
        this.setTreeLevelId(treeLevelId);
    }

    public BesharaTree(String s1, String s2, String treeId, 
                       String treeParentId, boolean hasChild, 
                       boolean booleanLeaf, boolean b1, String treeNodeLevel, 
                       String fullPathNodeName) {
        super(s1, s2, b1);
        this.setTreeId(treeId);
        this.setTreeParentId(treeParentId);
        this.setIdentifier(s2);
        this.setHasChild(hasChild);
        this.setBooleanLeaf(booleanLeaf);
        this.setTreeNodeLevelsID(treeNodeLevel);
        this.setFullPathNodeName(fullPathNodeName);
    }
    
    public BesharaTree(String s1, String s2, String treeId, String detailId, 
                       String treeParentId, boolean hasChild, 
                       boolean booleanLeaf, boolean b1) {
        super(s1, s2, b1);
        this.setTreeId(treeId);
        this.setDetailId(detailId);
        this.setTreeParentId(treeParentId);
        this.setIdentifier(s2);
        this.setHasChild(hasChild);
        this.setBooleanLeaf(booleanLeaf);
    }
    
    /*
     * add new Constructor
     * to store treeLevel for each node
     * By H.Ahmed
     * Nov. 2013
     */
    public BesharaTree(String s1, String s2, String treeId, String detailId, 
                       String treeParentId, boolean hasChild, 
                       boolean booleanLeaf, boolean b1,int treeLevelId) {
        super(s1, s2, b1);
        this.setTreeId(treeId);
        this.setDetailId(detailId);
        this.setTreeParentId(treeParentId);
        this.setIdentifier(s2);
        this.setHasChild(hasChild);
        this.setBooleanLeaf(booleanLeaf);
        this.setTreeLevelId(treeLevelId);
    }

    public BesharaTree(String s1, String s2, String treeId, String detailId, 
                       String treeParentId, boolean hasChild, 
                       boolean booleanLeaf, boolean b1, String treeNodeLevel) {
        super(s1, s2, b1);
        this.setTreeId(treeId);
        this.setDetailId(detailId);
        this.setTreeParentId(treeParentId);
        this.setIdentifier(s2);
        this.setHasChild(hasChild);
        this.setBooleanLeaf(booleanLeaf);
        this.setTreeNodeLevelsID(treeNodeLevel);
    }

    /*
     * add new Constructor
     * to store treeLevel for each node
     * By H.Ahmed
     * Nov. 2013
     */


    public BesharaTree(String s1, String s2, String treeId, String detailId, 
                       String treeParentId, boolean hasChild, 
                       boolean booleanLeaf, boolean b1, String treeNodeLevel,int treeLevelId) {
        super(s1, s2, b1);
        this.setTreeId(treeId);
        this.setDetailId(detailId);
        this.setTreeParentId(treeParentId);
        this.setIdentifier(s2);
        this.setHasChild(hasChild);
        this.setBooleanLeaf(booleanLeaf);
        this.setTreeNodeLevelsID(treeNodeLevel);
        this.setTreeLevelId(treeLevelId);
    }

     
    public int getIndex() {
        return parent == null ? 0 : getParent().getChildren().indexOf(this);
    }

    public String getPath() {
        String nodeIndex = "" + getIndex();
        StringBuilder sb = new StringBuilder(nodeIndex);
        for (BesharaTree n = getParent(); n != null; n = n.getParent()) {
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

    public void setParent(BesharaTree parent) {
        this.parent = parent;
    }

    public BesharaTree getParent() {
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
}
