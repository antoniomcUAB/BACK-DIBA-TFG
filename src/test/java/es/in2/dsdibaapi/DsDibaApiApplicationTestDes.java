package es.in2.dsdibaapi;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import es.in2.dsdibaapi.controller.DiagnosticController;
import es.in2.dsdibaapi.controller.PreguntaController;
import es.in2.dsdibaapi.model.Ambit;
import es.in2.dsdibaapi.model.AmbitDiagnostic;
import es.in2.dsdibaapi.model.Contextualitzacio;
import es.in2.dsdibaapi.model.Diagnostic;
import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.model.Expedient;
import es.in2.dsdibaapi.model.Factor;
import es.in2.dsdibaapi.model.FactorEconomic;
import es.in2.dsdibaapi.model.Frequencia;
import es.in2.dsdibaapi.model.Gravetat;
import es.in2.dsdibaapi.model.Municipi;
import es.in2.dsdibaapi.model.Persona;
import es.in2.dsdibaapi.model.Pregunta;
import es.in2.dsdibaapi.model.Professional;
import es.in2.dsdibaapi.model.Risc;
import es.in2.dsdibaapi.model.Rol;
import es.in2.dsdibaapi.model.SituacioSocial;
import es.in2.dsdibaapi.model.TipusPersona;
import es.in2.dsdibaapi.model.VersioModel;
import es.in2.dsdibaapi.service.AmbitService;
import es.in2.dsdibaapi.service.ContextualitzacioService;
import es.in2.dsdibaapi.service.DiagnosticService;
import es.in2.dsdibaapi.service.EconomiaService;
import es.in2.dsdibaapi.service.EntornService;
import es.in2.dsdibaapi.service.EstatService;
import es.in2.dsdibaapi.service.ExpedientService;
import es.in2.dsdibaapi.service.FactorEconomicService;
import es.in2.dsdibaapi.service.FactorService;
import es.in2.dsdibaapi.service.FrequenciaService;
import es.in2.dsdibaapi.service.GravetatService;
import es.in2.dsdibaapi.service.MunicipiService;
import es.in2.dsdibaapi.service.PersonaService;
import es.in2.dsdibaapi.service.PreguntaService;
import es.in2.dsdibaapi.service.ProfessionalService;
import es.in2.dsdibaapi.service.RiscService;
import es.in2.dsdibaapi.service.RolService;
import es.in2.dsdibaapi.service.SituacioSocialService;
import es.in2.dsdibaapi.service.TipusPersonaService;
import es.in2.dsdibaapi.service.VersioModelService;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DsDibaApiApplicationTestDes {
	
	@Autowired
	private PreguntaController preguntaController;
	
	@Autowired
	private AmbitService ambitService;
	
	@Autowired
	private DiagnosticController diagnosticController;
	
	
	@Autowired
	private EntornService entornService;
	
	
	@Autowired
	private ContextualitzacioService contextualitzacioService;
	
	@Autowired
	private SituacioSocialService situacioSocialService;
	
	@Autowired
	private RiscService riscService;
	
	@Autowired
	private FactorService factorService;
	
	
	@Autowired
	private FrequenciaService frequenciaService;
	
	@Autowired
	private GravetatService gravetatService;
	
	@Autowired
	private VersioModelService versioModelService;
	
	@Autowired
	private ProfessionalService professionalService;
	
	@Autowired
	private RolService rolService;
	
	@Autowired
	private EstatService estatService;
	
	@Autowired
	private MunicipiService municipiService;
	
	@Autowired
	private TipusPersonaService tipusPersonaService;
	
	Long gravetatBaixa=19240l;
	Long gravetatModerada=19241l;
	Long gravetaAlta =19242l;
	Long gravetaProteccio=19243l;
	Long gravetaRisc=19244l;
	
	Long riscSenseValoracio =19236l;
	Long riscVulnerabilitat =19237l;
	Long riscRisc=19238l;
	Long riscAltRisc=19239l;
	
	Long freqOcasional =  19231l;
	Long freqFrequent = 19232l;
	Long freqContinua = 19233l;
	Long freqPuntual = 19234l;
	Long freqSense = 19235l;
	
	Long ssA1 = 19256l;
	Long ssA2 = 19266l;
	
	Long ssH1 = 19278l;
	Long ssH2 = 19285l;
	
	Long ssES1 = 19364l;
	
	Long entornAutonomia = 19255l;
	Long entornHabitage = 19277l;
	Long entornEconomic = 19323l;
	Long entornEscolar = 19347l;
	
	Long rolTecnic = 19250l;
	
	Long versioId=19230l;
	
	Long municipiId = 19251l;
	
	Long factor1 = 19503l;
	Long factor2 = 19504l;
	
	Long factor3 = 19514l;
	Long factor4 = 19515l;
	Long factor5 = 19516l;
	Long factor6 = 19517l;
	
	Long factorEconomic1 = 19537l;
	Long factorEconomic2 = 19538l;
	
	Long pareMare = 19564l;
	
	Diagnostic diag;
	
	Entorn entorn;
	
	
	

	//@Test
	public void test2Valoracio() {
		
		TipusPersona mare = tipusPersonaService.findById(pareMare);
		
		Iterable<Ambit> ambits = ambitService.findAll(versioId);
		
		VersioModel versio;
		
		Persona persona1 = Persona.builder()
				.tipusPersona(mare)
				.sexe("h")
				.dataNaixement(new Date())
				.dataAlta(new Date ()).build();
		Persona persona2 = Persona.builder().referencia(true).tipusPersona(mare).dataAlta(new Date ()).sexe("d").dataNaixement(new Date()).build();
		
		Set<Rol> rols = new HashSet<Rol> ();		
		
		rols.add(rolService.findById(rolTecnic));
		
		Municipi municipi = municipiService.findById(municipiId);
		
		
		
		
		
		versio = versioModelService.findById(versioId);
		
		
		List<AmbitDiagnostic> ambitsDiagnostic = new ArrayList<AmbitDiagnostic> ();
		
		for (Ambit a:ambits) {				
			ambitsDiagnostic.add(AmbitDiagnostic.builder().ambit(a).build());
		}
		
		diag =Diagnostic.builder()
				.data(new Date())
				.estat(estatService.findByDescripcio(DiagnosticService.Estat.BORRADOR.toString()))
				.versioModel(versio)
				.ambit(ambitsDiagnostic)
				.observacions("Test 1").build();
		
		List<Diagnostic> diagnostics = new ArrayList<Diagnostic> ();
		
		diagnostics.add(diag);
		
		ambitsDiagnostic = new ArrayList<AmbitDiagnostic> ();
		
		for (Ambit a:ambits) {				
			ambitsDiagnostic.add(AmbitDiagnostic.builder().ambit(a).build());
		}
		
		diag =Diagnostic.builder()
				.data(new Date())
				.estat(estatService.findByDescripcio(DiagnosticService.Estat.BORRADOR.toString()))
				.versioModel(versio)
				.ambit(ambitsDiagnostic)
				.observacions("Test 2").build();
		
		diagnostics.add(diag);
		
		ambitsDiagnostic = new ArrayList<AmbitDiagnostic> ();
		
		for (Ambit a:ambits) {				
			ambitsDiagnostic.add(AmbitDiagnostic.builder().ambit(a).build());
		}
		
		diag =Diagnostic.builder()
				.data(new Date())
				.estat(estatService.findByDescripcio(DiagnosticService.Estat.BORRADOR.toString()))
				.versioModel(versio)
				.ambit(ambitsDiagnostic)
				.observacions("Test 3").build();
		
		diagnostics.add(diag);
		
		
		Expedient expedient = Expedient.builder()
				.codi("33AAAA999")
				.dataCreacio(new Date ())
				.estat(estatService.findByDescripcio(ExpedientService.Estat.INCOMPLET.toString()))		
				.totalFamilia(2l)
				.diagnostic(diagnostics)
				.persona(new HashSet<Persona>() {{
		            add(persona1);
		            add(persona2);
		        }})
				.build();
		
		List<Expedient> expedients = new ArrayList<Expedient> ();
		
		expedients.add(expedient);
		Professional professional = professionalService.save(
				Professional.builder().nom("Professional11")
									.cognom1("APE1")
									.cognom2("APE2")
									.municipi(municipi)
									.rol(rols)
									.expedient(expedients)
									.build()); 
		
		
		
		entorn = entornService.findById(entornAutonomia);
	
		Pregunta d;
		
		SituacioSocial situacioSocial;
	
		situacioSocial = situacioSocialService.findById(ssA1);
		
		Frequencia frequencia =frequenciaService.findById(freqOcasional);
		
		Risc risc = riscService.findById(riscVulnerabilitat);
		
		Gravetat gravetat = gravetatService.findById(gravetatBaixa);
		
		
		
		d = Pregunta.builder().situacioSocial(situacioSocial).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		
		
		
		String sdafsdggs= "";
		d=preguntaController.putPregunta(diag.getId(), d);
		
		
		assertTrue(d.getFactor().getDescripcio().equalsIgnoreCase("Vulnerabilitat"));
		
		Pregunta ssA1= d;
		
		
		situacioSocial = situacioSocialService.findById(ssA2);
		gravetat = gravetatService.findById(gravetatModerada);
		frequencia =frequenciaService.findById(freqContinua);
		
		d = Pregunta.builder().diagnostic(diag).situacioSocial(situacioSocial).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
	
		
		//diag = diagnosticService.findById(diag.getId());
		
		//Diagnostic newexp=diagnosticService.avaluar(diag);
		
		Diagnostic newexp=diagnosticController.getValoracio(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 5d);
		
		
		Factor factor = factorService.findById(factor1);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).membreUnic(true).factor(factor).build());
	
		newexp=diagnosticController.getValoracio(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 2.1d);
		
		entorn = entornService.findById(entornHabitage);
		
		situacioSocial = situacioSocialService.findById(ssH1);
		gravetat = gravetatService.findById(gravetatModerada);
		frequencia =frequenciaService.findById(freqOcasional);
		
		d = Pregunta.builder().diagnostic(diag).situacioSocial(situacioSocial).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
		newexp=diagnosticController.getValoracio(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 2.6d);
		
		frequencia =frequenciaService.findById(freqContinua);
		
		d.setFrequencia(frequencia);
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
		newexp=diagnosticController.getValoracio(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 4.6d);
		
		
		
		factor = factorService.findById(factor3);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).membreUnic(true).factor(factor).build());
		
		factor = factorService.findById(factor4);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).membreUnic(true).factor(factor).build());
		
		factor = factorService.findById(factor5);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).mesUc(true).factor(factor).build());
		
		factor = factorService.findById(factor6);
		
		contextualitzacioService.save(Contextualitzacio.builder().diagnostic(diag).membreUnic(true).factor(factor).build());
	
		newexp=diagnosticController.getValoracio(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 3.1d);
		
		
		
		 situacioSocial = situacioSocialService.findById(ssH2);
		gravetat = gravetatService.findById(gravetatModerada);
		frequencia =frequenciaService.findById(freqContinua);
		
		d = Pregunta.builder().diagnostic(diag).situacioSocial(situacioSocial).frequencia(frequencia).gravetat(gravetat).unitatFamiliar(true).build();
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
		newexp=diagnosticController.getValoracio(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 3.1d);
		
		entorn = entornService.findById(entornEconomic);
		
		situacioSocial = situacioSocialService.findById(ssES1);
		gravetat = gravetatService.findById(gravetatModerada);
		
		d = Pregunta.builder().diagnostic(diag).situacioSocial(situacioSocial).gravetat(gravetat).unitatFamiliar(true).build();
		
		d=preguntaController.putPregunta(diag.getId(), d);
		
		newexp=diagnosticController.getValoracio(diag.getId());
		
		assertTrue (newexp.getValoracio().getTotal() == 5.2d);
	
		
		
	}	
	
	@Test
	public void newProfessional () {
		
		Municipi municipi = municipiService.findById(municipiId);
		
		Set<Rol> rols = new HashSet<Rol> ();		
		
		rols.add(rolService.findById(rolTecnic));
		
		Professional professional = professionalService.save(
				Professional.builder().nom("Professional11")
									.cognom1("APE1")
									.cognom2("APE2")
									.municipi(municipi)
									.rol(rols)
									.build()); 
	}
	
}

