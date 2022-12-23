package model;


public class UserVO {
	
	
	// user_info table
	private int id;
	private String projectName;
	private String productName;
	private String createDate;
	private String dateModified;
	private String fu;
	private float productAmount;
	private String productDescript;
	
	private String impactCategory;
	private String lciaMethod;
	
	// inputs table
	private String[] inputs;
	private String[] iUnits;
	
	// outputs table
	private String[] outputs;
	private String[] oUnits;
	
	// result values
	private float result;
	private String resultUnit;
	private String flowName;
	private float lciResult1;
	private float charFactor1;
	private float flowLciaResult1;
	private float lciResult2;
	private float charFactor2;
	private float flowLciaResult2;

	// compare with openLCA
	private float result2;
	private float percent;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public String getDateModified() {
		return dateModified;
	}

	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}
	
	public String getFu() {
		return fu;
	}
	
	public void setFu(String fu) {
		this.fu = fu;
	}

	public float getProductAmount() {
		return productAmount;
	}
	
	public void setProductAmount(float productAmount) {
		this.productAmount = productAmount;
	}
	
	public String getImpactCategory() {
		return impactCategory;
	}
	
	public void setImpactCategory(String impactCategory) {
		this.impactCategory = impactCategory;
	}
	
	public String getLciaMethod() {
		return lciaMethod;
	}
	
	public void setLciaMethod(String lciaMethod) {
		this.lciaMethod = lciaMethod;
	}
	
	// inputs table
	public String[] getInputs() {
		return inputs;
	}
	
	public void setInputs(String[] inputs) {
		this.inputs = inputs;
	}
	
	public String[] getIUnits() {
		return iUnits;
	}
	
	public void setIUnits(String[] iUnits) {
		this.iUnits = iUnits;
	}

	// outputs table
	public String[] getOutputs() {
		return outputs;
	}
	
	public void setOutputs(String[] outputs) {
		this.outputs = outputs;
	}
	
	public String[] getOUnits() {
		return oUnits;
	}
	
	public void setOUnits(String[] oUnits) {
		this.oUnits = oUnits;
	}
	

	// save result values
	public float getResult() {
		return result;
	}
	
	public void setResult(float result) {
		this.result = result;
	}
	
	public String getResultUnit() {
		return resultUnit;
	}
	
	public void setResultUnit(String resultUnit) {
		this.resultUnit = resultUnit;
	}
	
	// result flows
	public String getFlowName() {
		return flowName;
	}
	
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	
	public float getLciResult1() {
		return lciResult1;
	}
	
	public void setLciResult1(float lciResult1) {
		this.lciResult1 = lciResult1;
	}
	
	public float getLciResult2() {
		return lciResult2;
	}
	
	public void setLciResult2(float lciResult2) {
		this.lciResult2 = lciResult2;
	}
	
	public float getCharFactor1() {
		return charFactor1;
	}
	
	public void setCharFactor1(float charFactor1) {
		this.charFactor1 = charFactor1;
	}
	
	public float getCharFactor2() {
		return charFactor2;
	}
	
	public void setCharFactor2(float charFactor2) {
		this.charFactor2 = charFactor2;
	}
	
	public float getFlowLciaResult1() {
		return flowLciaResult1;
	}
	
	public void setFlowLciaResult1(float flowLciaResult1) {
		this.flowLciaResult1 = flowLciaResult1;
	}
	
	public float getFlowLciaResult2() {
		return flowLciaResult2;
	}
	
	public void setFlowLciaResult2(float flowLciaResult2) {
		this.flowLciaResult2 = flowLciaResult2;
	}
	
	// compare with openLCA
	public float getResult2() {
		return result2;
	}
	
	public void setResult2(float result2) {
		this.result2 = result2;
	}
	
	public float getPercent() {
		return percent;
	}
	
	public void setPercent(float percent) {
		this.percent = percent;
	}
}
