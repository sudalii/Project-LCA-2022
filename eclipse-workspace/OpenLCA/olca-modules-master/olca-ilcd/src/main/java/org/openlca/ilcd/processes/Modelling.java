package org.openlca.ilcd.processes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.openlca.ilcd.commons.Other;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModellingAndValidationType", propOrder = {
		"method",
		"representativeness",
		"completeness",
		"validation",
		"complianceDeclarations",
		"other"
})
public class Modelling implements Serializable {

	private final static long serialVersionUID = 1L;

	@XmlElement(name = "LCIMethodAndAllocation")
	public Method method;

	@XmlElement(name = "dataSourcesTreatmentAndRepresentativeness")
	public Representativeness representativeness;

	public Completeness completeness;

	public Validation validation;

	@XmlElement(name = "complianceDeclarations")
	public ComplianceList complianceDeclarations;

	@XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
	public Other other;

	@XmlAnyAttribute
	public final Map<QName, String> otherAttributes = new HashMap<>();

	@Override
	public Modelling clone() {
		Modelling clone = new Modelling();
		if (method != null)
			clone.method = method.clone();
		if (representativeness != null)
			clone.representativeness = representativeness.clone();
		if (completeness != null)
			clone.completeness = completeness.clone();
		if (validation != null)
			clone.validation = validation.clone();
		if (complianceDeclarations != null) {
			clone.complianceDeclarations = complianceDeclarations.clone();
		}
		if (other != null)
			clone.other = other.clone();
		clone.otherAttributes.putAll(otherAttributes);
		return clone;
	}

}
