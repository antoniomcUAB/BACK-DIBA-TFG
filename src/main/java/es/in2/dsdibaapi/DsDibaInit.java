package es.in2.dsdibaapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import es.in2.dsdibaapi.model.Estat;
import es.in2.dsdibaapi.model.Frequencia;
import es.in2.dsdibaapi.model.Gravetat;
import es.in2.dsdibaapi.model.Municipi;
import es.in2.dsdibaapi.model.Risc;
import es.in2.dsdibaapi.model.Rol;
import es.in2.dsdibaapi.model.TipusPersona;
import es.in2.dsdibaapi.service.DiagnosticService;
import es.in2.dsdibaapi.service.EstatService;
import es.in2.dsdibaapi.service.ExpedientService;
import es.in2.dsdibaapi.service.FrequenciaService;
import es.in2.dsdibaapi.service.GravetatService;
import es.in2.dsdibaapi.service.MunicipiService;
import es.in2.dsdibaapi.service.RiscService;
import es.in2.dsdibaapi.service.RolService;
import es.in2.dsdibaapi.service.TipusPersonaService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@ConditionalOnProperty(name = "api.db-init", havingValue = "true")
public class DsDibaInit implements CommandLineRunner {
	
	@Autowired
	private RiscService riscService;
	
	@Autowired
	private FrequenciaService frequenciaService;
	
	@Autowired
	private GravetatService gravetatService;
	
	@Autowired
	private RolService rolService;
	
	@Autowired
	private MunicipiService municipiService;
	

	@Autowired
	private EstatService estatService;
	

	
	@Autowired
	private TipusPersonaService tipusPersonaService;
	
	
	@Override
	public void run(String... args) throws Exception {
		Frequencia ocasionalFrequencia= frequenciaService.save(Frequencia.builder().descripcio("Ocasional").value(1).build());
		Frequencia frequentFrequencia=frequenciaService.save(Frequencia.builder().descripcio("Freqüent").value(2).build());
		Frequencia continuaFrequencia=frequenciaService.save(Frequencia.builder().descripcio("Continua").value(3).build());
		Frequencia puntualFrequencia=frequenciaService.save(Frequencia.builder().descripcio("Puntual").value(0).build());
		Frequencia senseFrequencia=frequenciaService.save(Frequencia.builder().descripcio("Sense valoració").value(0).build());		
		
		Risc senseRisc = riscService.save(Risc.builder().descripcio("Sense Valoració").value(0).build());
		Risc vulnerabilitatRisc = riscService.save(Risc.builder().descripcio("Vulnerabilitat").value(1).build());
		Risc riscRisc = riscService.save(Risc.builder().descripcio("Risc").value(2).build());
		Risc alttRisc = riscService.save(Risc.builder().descripcio("Alt Risc").value(3).build());
		
		Gravetat baixaGravetat = gravetatService.save(Gravetat.builder().descripcio("Baixa").value(1).build());		
		Gravetat moderadaGravetat= gravetatService.save(Gravetat.builder().descripcio("Moderada").value(2).build());
		Gravetat  altaGravetat=gravetatService.save(Gravetat.builder().descripcio("Alta").value(3).build());
		Gravetat proteccioGravetat=gravetatService.save(Gravetat.builder().descripcio("Protecció").value(0).build());
		Gravetat riscGravetat=gravetatService.save(Gravetat.builder().descripcio("Risc").value(0).build());
		
		estatService.save(Estat.builder().descripcio(DiagnosticService.Estat.BORRADOR.toString()).build());
		estatService.save(Estat.builder().descripcio(DiagnosticService.Estat.VALIDAT.toString()).build());
		estatService.save(Estat.builder().descripcio(ExpedientService.Estat.INCOMPLET.toString()).build());
		estatService.save(Estat.builder().descripcio(ExpedientService.Estat.COMPLET.toString()).build());
		
		rolService.save(Rol.builder().descripcio("Gerent").build());
		rolService.save(Rol.builder().descripcio("Tècnic").codi(6121645l).build());
		
		municipiService.save(Municipi.builder().descripcio("Barcelona").build());
		municipiService.save(Municipi.builder().descripcio("Tarragona").build());
		municipiService.save(Municipi.builder().descripcio("LLeida").build());
		
		// Tipus de persones
		
		tipusPersonaService.save(TipusPersona.builder().descripcio("Altres").build());
		tipusPersonaService.save(TipusPersona.builder().descripcio("Avi/àvia,nét/a").build());
		tipusPersonaService.save(TipusPersona.builder().descripcio("Espòs/osa,company/a").build());
		tipusPersonaService.save(TipusPersona.builder().descripcio("Fill/a").build());
		tipusPersonaService.save(TipusPersona.builder().descripcio("Gendre/nora,sogre/a").build());
		tipusPersonaService.save(TipusPersona.builder().descripcio("Germà/ana,cunyat/ada").build());
		tipusPersonaService.save(TipusPersona.builder().descripcio("Nebot/da,cos/ina").build());
		tipusPersonaService.save(TipusPersona.builder().descripcio("Pare/mare").build());
		tipusPersonaService.save(TipusPersona.builder().descripcio("Persona principal").build());
		tipusPersonaService.save(TipusPersona.builder().descripcio("Tiet/a").build());
	}
	
	
}
