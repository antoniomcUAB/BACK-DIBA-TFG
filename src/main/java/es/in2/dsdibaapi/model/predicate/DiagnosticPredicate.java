package es.in2.dsdibaapi.model.predicate;

import java.util.List;
import java.util.function.Predicate;

import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.service.DiagnosticService;

public class DiagnosticPredicate {
	
	public static Predicate<Diagnostic> isValidat() {
		return d -> DiagnosticService.Estat.VALIDAT.toString().equalsIgnoreCase(d.getEstat().getDescripcio());
	}
	
	public static Long diagnosticsValidats (List<Diagnostic> diagnostics)
	{
		return diagnostics.stream()
		.filter( isValidat() ).count();
	}
}
