%dw 1.0
%output application/java
---
{
	errors: [{
		extendedErrorDetails: [{
		} as :object {
			class : "com.sforce.soap.partner.ExtendedErrorDetails"
		}],
		fields: ["????"],
		message: "????",
		statusCode: "????"
	} as :object {
		class : "com.sforce.soap.partner.Error"
	}],
	id: "????",
	success: true
} as :object {
	class : "com.sforce.soap.partner.SaveResult"
}