package es.in2.dsdibaapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import es.in2.dsdibaapi.model.Ambit;
import es.in2.dsdibaapi.model.Criteri;
import es.in2.dsdibaapi.model.Entorn;
import es.in2.dsdibaapi.model.Factor;
import es.in2.dsdibaapi.model.FactorEconomic;
import es.in2.dsdibaapi.model.FactorGravetat;
import es.in2.dsdibaapi.model.Frequencia;
import es.in2.dsdibaapi.model.FrequenciaGravetat;
import es.in2.dsdibaapi.model.Gravetat;
import es.in2.dsdibaapi.model.Persona;
import es.in2.dsdibaapi.model.Risc;
import es.in2.dsdibaapi.model.SituacioSocial;
import es.in2.dsdibaapi.repository.AmbitRepository;
import es.in2.dsdibaapi.repository.CriteriRepository;
import es.in2.dsdibaapi.repository.EntornRepository;
import es.in2.dsdibaapi.repository.ExpedientRepository;
import es.in2.dsdibaapi.repository.FactorEconomicRepository;
import es.in2.dsdibaapi.repository.FactorGravetatRepository;
import es.in2.dsdibaapi.repository.FactorRepository;
import es.in2.dsdibaapi.repository.FrequenciaGravetatRepository;
import es.in2.dsdibaapi.repository.FrequenciaRepository;
import es.in2.dsdibaapi.repository.GravetatRepository;
import es.in2.dsdibaapi.repository.PersonaRepository;
import es.in2.dsdibaapi.repository.RiscRepository;
import es.in2.dsdibaapi.repository.SituacioSocialRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@ConditionalOnProperty(name = "api.db-init", havingValue = "true")
public class DataBaseInit implements CommandLineRunner {
	
	@Autowired
	private AmbitRepository ambitRepository; 
	
	@Autowired
	private RiscRepository riscRepository;
	
	@Autowired
	private FrequenciaRepository frequenciaRepository;
	
	@Autowired
	private GravetatRepository gravetatRepository;
	
	@Autowired
	private EntornRepository entornRepository;
	
	@Autowired
	private SituacioSocialRepository situacioSocialRepository;
	
	@Autowired
	private FrequenciaGravetatRepository frequenciaGravetatRepository;
	
	@Autowired
	private CriteriRepository criteriRepository;
	
	@Autowired
	private FactorRepository factorRepository;
	
	@Autowired
	private FactorGravetatRepository factorGravetatRepository;
	
	@Autowired
	private FactorEconomicRepository factorEconomicRepository;
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	private ExpedientRepository expedientRepository;
	
	@Value("${add.test}")
	private Boolean addTest;

	@Override
	public void run(String... args) throws Exception {
		
		
		
		Frequencia ocasionalFrequencia=frequenciaRepository.save(new Frequencia("Ocasional"));
		Frequencia frequentFrequencia=frequenciaRepository.save(new Frequencia("Freq�ent"));
		Frequencia continuaFrequencia=frequenciaRepository.save(new Frequencia("Continua"));
		Frequencia puntualFrequencia=frequenciaRepository.save(new Frequencia("Puntual"));
		Frequencia senseFrequencia=frequenciaRepository.save(new Frequencia("Sense valoraci�"));		
		
		Risc vulnerabilitatRisc = riscRepository.save(new Risc("Vulnerabilitat"));
		Risc riscRisc = riscRepository.save(new Risc("Risc"));
		Risc alttRisc = riscRepository.save(new Risc("Alt Risc"));
		
		Gravetat baixaGravetat = gravetatRepository.save(new Gravetat("Baixa"));		
		Gravetat moderadaGravetat= gravetatRepository.save(new Gravetat("Moderada"));
		Gravetat  altaGravetat=gravetatRepository.save(new Gravetat("Alta"));
		Gravetat proteccioGravetat=gravetatRepository.save(new Gravetat("Protecci�"));
		Gravetat riscGravetat=gravetatRepository.save(new Gravetat("Risc"));
		
		Ambit ambit = ambitRepository.save(new Ambit ("Autonomia"));
		
		Entorn entornAutonomia = entornRepository.save (new Entorn ("Autonomia",ambit));
		
		SituacioSocial situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Manca d�autonomia personal a les  activitats b�siques de la vida diaria  (A.1)",
																						"Situaci� en qu� la persona no pot desenvolupar les activitats b�siques de la vida diaria (alimentaci�, higiene, seguiment m�dic i farmacol�gic, mobilitat personal, descans, etc.), independentment de la causa que la motiva: malaltia f�sica o ps�quica, trastorn mental, deteriorament cognitiu, discapacitat f�sica, ps�quica,  intel�lectual o sensorial, etc.",entornAutonomia));
		
		FrequenciaGravetat frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Necessita suport/atenci�/supervisi� en algun moment puntual de la setmana/dia",baixaGravetat));
		
		criteriRepository.save(new Criteri ("P�rdua sobtada per causes sobrevingudes i possiblitats de millora",ocasionalFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Cr�nica, sense possiblitats de millora",continuaFrequencia,riscRisc,frequenciaGravetat));
		
		
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Necessita suport/atenci�/supervisi� per les activitats de la vida di�ria",moderadaGravetat)); 
		
		criteriRepository.save(new Criteri ("P�rdua sobtada per causes sobrevingudes i possiblitats de millora",ocasionalFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Cr�nica, sense possiblitats de millora",continuaFrequencia,alttRisc,frequenciaGravetat));
		
		
		frequenciaGravetat = frequenciaGravetatRepository.save(
									new FrequenciaGravetat(situacioSocial,
											"Necessita atenci�/supervisi� continua les 24h",
											moderadaGravetat)); 
		
		criteriRepository.save(new Criteri ("P�rdua sobtada per causes sobrevingudes i possiblitats de millora",ocasionalFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Cr�nica, sense possiblitats de millora",continuaFrequencia,alttRisc,frequenciaGravetat));
				
				
		situacioSocial = situacioSocialRepository.save(
							new SituacioSocial ("Manca d�autonomia personal a les  activitats b�siques de la vida diaria  (A.1)",
												"Situaci� en qu� la persona no pot desenvolupar les activitats instrumentals de la vida diaria (utilitzaci� del transport p�blic, realitzaci� de tasques dom�stiques, responsabilitat sobre la medicaci�, administraci� de diners, capacitat d�utilitzar el tel�fon, entrar i sortir del domicili de forma aut�noma, etc.), independentment de la causa que la motiva: malaltia f�sica o ps�quica, trastorn mental, deteriorament cognitiu, discapacitat f�sica, ps�quica,  intel�lectual o sensorial, etc.",
												entornAutonomia));
		
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Necessita atenci�/supervisi� puntual en algun moment del dia/setmana",
						baixaGravetat)); 
		
		criteriRepository.save(new Criteri ("P�rdua sobtada per causes sobrevingudes i possiblitats de millora",ocasionalFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Cr�nica, sense possiblitats de millora",continuaFrequencia,riscRisc,frequenciaGravetat));
		
		
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Necessita suport/atenci�/supervisi� per les activitats de la vida di�ria",moderadaGravetat));
		

		criteriRepository.save(new Criteri ("P�rdua sobtada per causes sobrevingudes i possiblitats de millora",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Cr�nica, sense possiblitats de millora",puntualFrequencia,alttRisc,frequenciaGravetat));
		
		
		
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Necessita atenci�/supervisi� continua les 24h",altaGravetat));		
		
		criteriRepository.save(new Criteri ("P�rdua sobtada per causes sobrevingudes i possiblitats de millora",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Cr�nica, sense possiblitats de millora",puntualFrequencia,alttRisc,frequenciaGravetat));						
		
		
		ambit = ambitRepository.save(new Ambit ("MATERIAL I INSTRUMENTAL"));				
				
		Entorn entornHabitatge = entornRepository.save (new Entorn ("Entorn habitatge",ambit));
								
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Manca d'habitatge (H.1)","Situacions de manca d'un habitatge estable on fixar la resid�ncia. Inclou persones en situacions de sense sostre o sense llar de les categories ETHOS i inclou persones acollides temporalment en una instituci� a trav�s de xarxa o en domicilis de familiars o amics persones que perden l'acolliment al domicili on vivien o que fixen el seu domicili en habitacles que no es consideren un habitatge (per exemple un cotxe).",entornHabitatge));
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Necessita suport/atenci�/supervisi� en algun moment puntual de la setmana/dia",moderadaGravetat));
		criteriRepository.save(new Criteri ("Sobrevinguda",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("M�s d'un cop a l'any",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Sense sostre",puntualFrequencia,alttRisc,frequenciaGravetat));
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Sense xarxa de suport ni possiblitat d'activar recurs d'habitage. Amb xarxa de suport o habitatge temporal d'urg�ncia i pres�ncia de menors o dependents a c�rrec",altaGravetat));
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,alttRisc,frequenciaGravetat));
								
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Habitatge deficient (H.2)","Situacions que es corresponen a la categoria ETHOS habitatge inadequat i inclou domicili que no reuneix les condicions adequades d'habitabilitat o les necess�ries per garantir l'auton�mia i qualitat de vida de les persones que hi habiten. Inclou infrahabitatge (caravana i barraquisme). Inclou les situacions relacionades amb manca d'higiene/neteja greus.",entornHabitatge));
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Sense risc en  la integritat f�sica de les persones que hi habiten.",moderadaGravetat));
		criteriRepository.save(new Criteri ("Situacions excepcionals i restaruables",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Situacions estructurals ",puntualFrequencia,riscRisc,frequenciaGravetat));
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Existeix risc en la integritat f�sica de les persones que hi habiten ",altaGravetat));
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,alttRisc,frequenciaGravetat));
								
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Habitatge insegur (H.3)","	",entornHabitatge));
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Situaci� impagament pr�via a l�inici del proc�s judicial. Ocupaci� il�legal",baixaGravetat));
		criteriRepository.save(new Criteri ("Situaci� puntual i capcitat de resoluci�",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Inferior 3 mesos consecutius",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("M�s de 3 mesos. Endeutament creixent",puntualFrequencia,riscRisc,frequenciaGravetat));
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Proc�s judicial iniciat",moderadaGravetat));
		criteriRepository.save(new Criteri ("Capacitat mediaci�. Morat�ria",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("En proc�s judicial sense capacitat mediaci�. 	Morat�ria",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("En subhasta",puntualFrequencia,alttRisc,frequenciaGravetat));
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Sent�ncia amb ordre de llan�ament",altaGravetat));
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,alttRisc,frequenciaGravetat));
								
								
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Habitatge massificat (H.4)","Situaci� d'habitatge massificat que es troba a la categoria ETHOS d'habitatge inadequat. Relaci� inadequada entre el nombre de persones que conviuen en un domicili i la superf�cie de l'habitatge, que no permet satisfer les activitats de la vida di�ria de forma satisfact�ria. / salubritat llei habitatge.",entornHabitatge));
								
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Un sol nucli familiar en espai redu�t. Persones joves que comparteixen espais",baixaGravetat));
							
		criteriRepository.save(new Criteri ("Des de fa menys de 3 mesos i de manera no repetida en el temps",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Fa m�s de 3 mesos",puntualFrequencia,riscRisc,frequenciaGravetat));
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"�Invasi� familiar�: existeix un nucli familiar i arriben de forma sobtada nous membres.",moderadaGravetat));
		criteriRepository.save(new Criteri ("Des de fa menys de 3 mesos i de manera no repetida en el temps",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Fa m�s de 3 mesos",puntualFrequencia,riscRisc,frequenciaGravetat));
								
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Diverses fam�lies amb menors o dependents compartint espais. Situacions de relloguers sense contracte.",altaGravetat));
		criteriRepository.save(new Criteri ("Des de fa menys de 3 mesos i de manera no repetida en el temps",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Fa m�s de 3 mesos",puntualFrequencia,alttRisc,frequenciaGravetat));
								
								
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Risc de p�rdua o manca serveis/subministraments (H.5)","Risc de perdua o manca de subministraments (gas, aigua, electricitat), manca de parament b�sic o estris dom�stics imprescindibles per satisfer les necessitats b�siques de la unitat familiar. Inclou els risc de perdua per connexi� il�legal.",entornHabitatge));
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Av�s de tall. Manca de subministrament i �s possible el restabliment",baixaGravetat));
		criteriRepository.save(new Criteri ("Primer cop",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Segon cop durant l�any",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("M�s de 2 cops durant any",puntualFrequencia,riscRisc,frequenciaGravetat));
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Av�s de tall i manca de m�s d'un subministrament per� �s possible el restabliment",moderadaGravetat));
		criteriRepository.save(new Criteri ("Primer cop",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Segon cop durant l�any",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("M�s de 2 cops durant any",puntualFrequencia,riscRisc,frequenciaGravetat));
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"No es pot restablir el servei",altaGravetat));
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,alttRisc,frequenciaGravetat));

		
		Entorn entornEconomic = entornRepository.save (new Entorn ("Entorn Econ�mic",ambit));

		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Sense ingressos (E.1)","Unitats familiars que no tenen ingressos o que no els acrediten de forma estable o regular. Es proposa Comptabilitzar en aquesta categoria aquelles unitats familiars on s�han identificat m�s de 6 �tems dels q�estionari.",entornEconomic));
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Disposen de xarxa o recursos que proporcionen  suport econ�mic. No han d�afrontar pagament de l�habitatge habitual",moderadaGravetat));
		criteriRepository.save(new Criteri ("De forma puntual/sobrevinguda i amb capacitat de canvi",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("De forma continua/cr�nica",puntualFrequencia,alttRisc,frequenciaGravetat));	
							
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"No disposen de xarxa o recursos de suport econ�mic",moderadaGravetat));	
									
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,alttRisc,frequenciaGravetat));	
							
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Ingressos insuficients (E.2)","Unitats familiars que malgrat tenir uns ingressos tenen dificultats per cobrir determinades necessitats  dels seus integrants.",entornEconomic));
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"3-4 �tems del q�estionari privaci� econ�mica i amb xarxa/recursos de suport econ�mic",baixaGravetat));	
									
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));	
							
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"3-4 �tems del q�estionari sense xarxa de suport o. 5-6 amb xarxa familiar de suport que proporciona els recursos",moderadaGravetat));	
									
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,riscRisc,frequenciaGravetat));						
								
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"5 o m�s �tems del q�estionari sense xarxa/recursos de suport econ�mic",altaGravetat));
									
		criteriRepository.save(new Criteri ("De forma puntual/sobrevinguda i amb capacitat de canvi",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("De forma continua i poca capacitat de canvi",puntualFrequencia,alttRisc,frequenciaGravetat));	
							
							
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Administraci� deficient (E.3)","Unitat familiar amb ingressos per� que presenta dificultats per administrar el pressupost  i no prioritzar la cobertura de les necessitats b�siques.",entornEconomic));
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"No saben fer front a despeses extres inesperades",baixaGravetat));
		criteriRepository.save(new Criteri ("Situacions concretes  i capacitat de canvi",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("De forma continua",puntualFrequencia,riscRisc,frequenciaGravetat));	
							
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Degut a la mala gesti� tenen dificultats per cobrir les despeses b�siques de la unitat familiar ",moderadaGravetat));
		criteriRepository.save(new Criteri ("Situacions concretes  i capacitat de canvi",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("De forma continua",puntualFrequencia,alttRisc,frequenciaGravetat));						
							
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"La mala gesti� �s cr�ncia i amb greus afectacions a la v�da di�ria",altaGravetat));	
									
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,alttRisc,frequenciaGravetat));	
										
		Entorn entornLaboral = entornRepository.save (new Entorn ("Entorn Laboral",ambit));
											
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Manca de qualificaci� i/o d'orientaci� laboral  (L.1)","La persona t� dificultats per trobar una feina estable perqu� desconeix els mecanismes per accedir al mercat laboral i no disposa de la formaci� i qualificaci� suficient per trobar una feina estable. En conseq��ncia, entra i surt del mercat laboral, realitza treballs que no donen continu�tat a la seva progressi� professional o resta llargues temporades a l'atur.  Addicionalment, la persona pot tenir dificultats personals o legals que dificulten l'acc�s al mercat de treball o el manteniment d'una  feina.",entornLaboral));		
																	
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Voluntat de treballar per� dificultat en l�acc�s al mercat de treball. Situacions sobrevingudes (salut, situaci� legal en tr�mit, etc..)",baixaGravetat));		
		criteriRepository.save(new Criteri ("Ha treballat en el darrer any",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Ha treballat 6 mesos dels darrers 3 anys",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Atur de llarga durada/ fa m�s de 3 anys que no treballa",puntualFrequencia,riscRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Les dificultats en el manteniment de les carregues familiars i la cura agreujen la situaci� ",altaGravetat));		
		criteriRepository.save(new Criteri ("Ha treballat en el darrer any",frequentFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Ha treballat 6 mesos dels darrers 3 anys",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Atur de llarga durada/ fa m�s de 3 anys que no treballa",puntualFrequencia,alttRisc,frequenciaGravetat));	
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Manca de compet�ncies professionals transversals  (L.2)","La persona desconeix o no t� les compet�ncies professionals necess�ries per accedir al mercat laboral i mantenir una feina. En conseq��ncia, entra i surt del mercat laboral per motius que tenen a veure amb l'actitud i la responsabilitat cap a la feina (inpuntualitat, manca de respecte als companys/es de feina o superior jer�rquic, imatge personal no adaptada als requeriments de la feina, manca d'anticipaci� de problem�tiques, etc).",entornLaboral));		
																	
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Consci�ncia de canvi i voluntat de millora",baixaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));								
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Impossibilitat de canvi o millora a mig termini (1 any)",altaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,riscRisc,frequenciaGravetat));	
											
											
		ambit = ambitRepository.save(new Ambit ("RELACIONAL"));						
											
		Entorn entornEscolar = entornRepository.save (new Entorn ("Entorn Escolar",ambit));
											
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Dificultats d'aprenentatge escolar (ES.1)","Infant o adolescent que presenta dificultats en el seguiment educatiu que li correspon com proc�s evolutiu, com a conseq��ncia de din�miques familiars adverses (inclouen desvinculaci� del centre educatiu, manca de recolzament, etc.).",entornEscolar));		
																	
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Dificultats associades a les capacitats de l'infant, identificades i existeix una intervenci� conjunta per part dels diferents serveis. Situacions puntuals que afecten a la seva escolaritat",baixaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Encara no existeix intervenci� conjunta per part dels diferents serveis, possiblitats de canvi i millora de la situaci�.",moderadaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,riscRisc,frequenciaGravetat));
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Fam�lia que entorpeix la intervenci� social ni accepta el treball. Afectacions molt greus en el desenvolupament integral de l�infant o adolescent",altaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,alttRisc,frequenciaGravetat));		
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Manca de compet�ncies relacionals a l'escola (ES.2)","Infant o adolescent que no s'adapta al sistema educatiu en l'etapa obligat�ria i que presenta conductes disruptives al centre, amb els adults i/o amb els seus iguals.",entornEscolar));		
																	
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial," Dificultats identificades i existeix una intervenci� conjunta per part dels diferents serveis",baixaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Encara no existeix intervenci� conjunta per part dels diferents serveis, possiblitats de canvi i millora de la situaci�.",moderadaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,riscRisc,frequenciaGravetat));
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Fam�lia que entorpeix la intervenci� social ni accepta el treball. Afectacions molt greus en el desenvolupament integral de l�infant o adolesecent. Comportaments agressius envers companys o mestres",altaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,alttRisc,frequenciaGravetat));	
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Escolaritat obligat�ria inacabada (ES.3)","Menor en edat d'escolaritzaci� obligat�ria que no est� matriculat en cap centre, o que ha abandonat els estudis abans de la seva finalitzaci�.",entornEscolar));		
																	
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Situaci� puntual que afecta a la tramitaci� de la seva escolaritat per� amb voluntat i capacitat de resoluci� en un curt termini (mateix curs escolar)",baixaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Situaci� amb marge de millora que requereix una intervenci� conunta  dels diferents serveis i recursos especialitzats.",moderadaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,riscRisc,frequenciaGravetat));
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial," No hi ha voluntat de canvi ni millora, La fam�lia incentiva o no col�labora per resoldre la situaci�. Afectacions molt greus en el desenvolupament integral de l�infant o adolesecent. Comportaments agressius envers companys o mestres",altaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,alttRisc,frequenciaGravetat));		
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Absentisme escolar (ES.4)","No assist�ncia al centre en que esta matriculat, de forma reiterada i no justificada, de l�alumnat en edat escolar (dels 3 als 16 anys) ja sigui per pr�pia voluntat o per voluntat de la fam�lia.",entornEscolar));		
																	
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Situaci� puntaul per causa sobrevenguida (Malaltia o motius de salut/ familiars)",baixaGravetat));				
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));	
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Encara no existeix intervenci� conjunta per part dels diferents serveis, possiblitats de canvi i millora de la situaci�.",moderadaGravetat));		
		criteriRepository.save(new Criteri ("De 2 a 5 vegades al mes",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("M�s de 5 vegades al mes",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("No assiteix m�s de 5 dies consecutius",puntualFrequencia,riscRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"La fam�lia incentiva o no col�labora per resoldre l'absentisme",altaGravetat));		
		criteriRepository.save(new Criteri ("De 2 a 5 vegades al mes",frequentFrequencia,alttRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("M�s de 5 vegades al mes",continuaFrequencia,alttRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("No assiteix m�s de 5 dies consecutius",puntualFrequencia,alttRisc,frequenciaGravetat));					
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Assetjament escolar (ES.5)","Infant o adolescent que est� patint Situacions habituals de viol�ncia f�sica i ps�quica en el marc de l�escola, o que s�ha iniciat en aquest marc encara que les actuacions es produeixin fora, per part dels seus companys i companyes, ja sigui en l�entorn escolar o fora d�ell",entornEscolar));		
																	
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial," S'identifiquen situacions de comentaris despectius provinents d'una sola persona.",baixaGravetat));					
		criteriRepository.save(new Criteri ("Fet puntual o en un per�ode de m�xim un mes",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Es repeteix durant tot un trimestre",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("De forma reiterada i en m�s d'un trimestre",puntualFrequencia,riscRisc,frequenciaGravetat));		
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"S'identifiquen  situacions d'agressions verbals per part de grup redu�t",moderadaGravetat));					
		criteriRepository.save(new Criteri ("Fet puntual o en un per�ode de m�xim un mes",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Es repeteix durant tot un trimestre",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("De forma reiterada i en m�s d'un trimestre",puntualFrequencia,alttRisc,frequenciaGravetat));	
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Agressions f�siques o agressions verbals provinents de gran grup",altaGravetat));					
		criteriRepository.save(new Criteri ("Fet puntual o en un per�ode de m�xim un mes",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Es repeteix durant tot un trimestre",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("De forma reiterada i en m�s d'un trimestre",puntualFrequencia,alttRisc,frequenciaGravetat));
											
		Entorn entornFamiliar = entornRepository.save (new Entorn ("Entorn Familiar",ambit));
											
				
											
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Maltractament (F.1)","Situacions de maltractament que rep un menor d'edat,  adult o  persona gran, inclou vexacions de car�cter f�sic, psicol�gic o emocional, aix� com les situacions de viol�ncia de g�nere. Inclou la sospita per part del professional o b� l'evid�ncia que el maltractament s'est� produint.(Es consideren incloses en quest indicador totes les categories de maltractament relacionades al RUDEL)",entornFamiliar));		
											
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Sense perill f�sic i l�afectaci� a nivell ps�quic/emocional no interfereix en les compet�ncies b�siques de la persona (autocura, comunicaci�, autonomia i iniciativa)",moderadaGravetat));					
		criteriRepository.save(new Criteri ("1 cop a l'any/ vida",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("M�s d'un cop en el darrer any",continuaFrequencia,riscRisc,frequenciaGravetat));			
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,alttRisc,frequenciaGravetat));		
											
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Ab�s Sexual (F.2)","La participaci� del nen/a i/o adolescent, adult o persona gran, en activitats sexuals que no est� en condicions de comprendre, que s�n inapropiades per a la seva edat i el seu desenvolupament psicosexual, per a les quals �s incapa� de donar el seu consentiment i que transgredeixen els tab�s i les regles familiars i socials, aix� com les lleis vigents. En l�ab�s sexual existeixen dos factors clau: la coerci� i la asimetria d�edat o de moment evolutiu entre les persones implicades.",entornFamiliar));		
											
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"En qualsevol situaci�",altaGravetat));			
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,alttRisc,frequenciaGravetat));			
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Neglig�ncia amb persones vulnerables (F.3)","Situacions en les quals les necessitats b�siques (f�siques, socials, psicol�giques) d'una persona vulnerable del nucli familiar (menor, depenent, gent gran , etc.) no s�n ateses de manera temporal o permanent per cap dels membres del grup on conviu. Val a dir que situacions de neglig�ncia cronificada amb conseq��ncies greus per a la salut i el benestar de la persona que els rep s'han de considerar una forma de maltractament.",entornFamiliar));		
											
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Sense perill f�sic i l�afectaci� a nivell ps�quic/emocional no interfereix en les compet�ncies b�siques de la persona (autocura, comunicaci�, autonomia i iniciativa).",moderadaGravetat));			
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,riscRisc,frequenciaGravetat));		
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Afectacions greu per la integritat de la persona",altaGravetat));					
		criteriRepository.save(new Criteri ("Situaci� puntual",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Situaci� reiterada",puntualFrequencia,alttRisc,frequenciaGravetat));	
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Dificultats per assumir responsabilitats familiars i socials (F.4)","Persones adultes que no s�n capaces d'assumir adequadament les seves responsabilitats en el si del nucli familiar, respecte de les persones que en depenen legalment",entornFamiliar));		
											
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial," Manca de l�mits adequats a l�edat dels infants/adolescents/. No s�involucra activament en el desenvolupament/cura de la persona. Un altre adult assumeix les responsabilitats.Voluntat de canvi i capacitat de millora",baixaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Manca de l�mits  o de cura de les persones que en depenen i ning� dins del nucli de conviv�ncia assumeix les responsabilitats. Voluntat de canvi i capacitat de millora",moderadaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,riscRisc,frequenciaGravetat));	
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"No hi ha acceptaci� de la situaci� ni capacitat de millora a curt termini. Un/a menor assumeix les responsabilitats",altaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,alttRisc,frequenciaGravetat));		
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Estr�s derivat de la situaci� familiar (F.5)","Situacions d'estr�s derivades de canvis impactant en l'entorn familiar per situacions sobrevingudes, o com a conseq��ncia d'una sobrec�rrega motivada per les din�miques familiars.  Inclou risc de claudicaci� de cuidadors, si v�nen motivats per un estat d'ansietat i sobrec�rrega.",entornFamiliar));		
											
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Relacionat a fets concrets sobrevinguts.",baixaGravetat));		
																
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,alttRisc,frequenciaGravetat));	
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Instaurat en les din�miques familiars sense que  interfereixi en les activitats b�siques.",moderadaGravetat));		
																
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Afectacions permanents en la cura i les responsabilitats familiars",altaGravetat));		
																
		criteriRepository.save(new Criteri ("Porta menys de 6 mesos",continuaFrequencia,riscRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Porta m�s de 6 mesos",puntualFrequencia,alttRisc,frequenciaGravetat));
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Sobre-responsabilitat (F.6)","Membre de la unitat de conviv�ncia, adult o menor, que assumeix responsabilitats familiars que es considera que es troben per sobre de les seves possibilitats. Aqu� quedaria incl�s l�embar�s adolescent Amb menors cal tenir en compte: L�activitat causant de la sobre-responsabilitat- La maduresa i edat de l�infant. - La maduresa de la fam�lia (si la fam�lia vetlla o no per minimitzar l�impacte de la responsabilitat atribu�da al menor).- Si disposa de la supervisi� d�algun adult de l�entorn de proximitat.- El temps que s�exposa a la sobre-responsabilitat",entornFamiliar));		
											
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Relacionat a fets concrets sobrevinguts.",baixaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));				
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"La situaci� afecta a un adult amb mancances per� amb capcitat de donar resposta a la situaci� mitjan�ant el suport necessari. La situaci� afecta a un/a menor major de 16 anys, sense que afecti greument a la seva integritat piscol�gica i amb possiblitat de canvis i millora.",moderadaGravetat));		
																
		criteriRepository.save(new Criteri ("Menys de 2 mesos",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Entre 2 i 6 mesos",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));					
		criteriRepository.save(new Criteri ("M�s de 6 mesos",puntualFrequencia,alttRisc,frequenciaGravetat));		
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Afectaci� greu a menors o persones dependents. La situaci� afecta a un adult que no la reconeix ni accepta el suport necessari.",altaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,alttRisc,frequenciaGravetat));				
																
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Dificultats en les relacions familiars (F.7)","Persona que presenta dificultats de relaci� i comportaments agressius amb un o m�s membres de la unitat de conviv�ncia.",entornFamiliar));		
											
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Fets molt concrets, amb reconeixement i voluntat de canvi. No instaurat en la din�mica familiar. Sense infants en el nucli de conviv�ncia.",baixaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));				
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Instaurat en la din�mica familiar per� amb voluntat de canvi i millora. Amb infants en el nucli de conviv�ncia",moderadaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,riscRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Afectaci� greu en la din�mica familiar i la salut f�sica/emocional dels seus membres",altaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,alttRisc,frequenciaGravetat));						
																
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Manca de recursos personals i autonomia en la presa de decisions(F.8)","Situaci� en la qual la persona mostra incapacitat d'afrontar aspectes de la vida quotidiana en l'�mbit de l'organitzaci� familiar, econ�mic,  laboral, de relaci� social, etc.",entornFamiliar));		
											
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Situaci� recent, motivada per fets externs i reversible amb  recursos personals",baixaGravetat));		
																		
		criteriRepository.save(new Criteri ("Passa puntualment",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Passa de manera reiterada",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));					
		criteriRepository.save(new Criteri ("Continu",puntualFrequencia,riscRisc,frequenciaGravetat));							
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Situaci�  reversible amb suport extern",moderadaGravetat));		
																		
		criteriRepository.save(new Criteri ("Passa puntualment",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));
		criteriRepository.save(new Criteri ("Passa de manera reiterada",continuaFrequencia,riscRisc,frequenciaGravetat));					
		criteriRepository.save(new Criteri ("Continu",puntualFrequencia,riscRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Situaci� irreversibale i amb necessitat de suport",altaGravetat));		
																		
		criteriRepository.save(new Criteri ("Continu",frequentFrequencia,vulnerabilitatRisc,frequenciaGravetat));					
																	
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Manca d'higiene o desordre al domicili (F.9)","Domicili que, degut al comportament d�algun del seus habitants, reuneix condicions d�habitabilitat per� presenta un mal estat d'higiene general o uns elevats nivells de desordre i/o acumulaci� d'estris o material divers, que dificulten la vida quotidiana de les persones que hi viuen. Inclou els casos vinculats a una malaltia mental (S�ndrome de Diogenes o de No�)",entornFamiliar));		
											
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Situaci� puntual i reversible amb el suport adequat. Persones diagnosticades que col�laboren.",baixaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));				
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Situaci� reiterada amb necessitat de suport extern. Persones diagnosticades que no col�laboren",moderadaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,riscRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Pres�ncia de menors o dependents al domicili. Greus afectacions per la integritat f�sica de les persones. Ind�cis de malatia mental no diagnosticada ni en tractament",altaGravetat));		
																		
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,alttRisc,frequenciaGravetat));						
																
													
		
		Entorn entornSocial = entornRepository.save (new Entorn ("Entorn Social",ambit));
											
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Dificultats en les relacions socials i a la participaci� a la comunitat (S.1)","Persona amb dificultats per adaptar-se al seu medi social (a�llament, no respecta els l�mits normatius ni socials del seu entorn).",entornSocial));		
														
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Existeix algun xarxa relacional de suport tot i que es detecten dificultats per crear o mantenir noves relacions. Participa en alguns entorns puntuals per� presenta una manca d�habilitats i capacitats per participar a la comunitat",baixaGravetat));		
														
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));					
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Manca de motivaci� i voluntat per relacionar-se amb l�entorn comunitari. A�llament social. Comportaments agressius en l'entorn. Els contactes s�n amb un entorn relacional t�xic/negatiu",moderadaGravetat));		
																
		criteriRepository.save(new Criteri ("Puntual. Conjuntural amb possibilitats de canvi",frequentFrequencia,riscRisc,frequenciaGravetat));					
		criteriRepository.save(new Criteri ("Continu",continuaFrequencia,riscRisc,frequenciaGravetat));			
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Rebuig social (S.2)","Persona socialment a�llada i/o rebutjada per raons f�siques,  de g�nere, �tnia, religi�, orientaci� sexual, conducta o qualsevol altra ra�.",entornSocial));		
														
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Rebuig en un sol entorn i sense afectacions a la integritats f�sica o emocional de la personal",moderadaGravetat));		
														
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,riscRisc,frequenciaGravetat));		
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"En m�s d�un entorn. Amb afectacions a la integritat de la persona",altaGravetat));		
														
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,alttRisc,frequenciaGravetat));					
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Addiccions (S.3)","Persona que fa un �s abusiu d'alguna subst�ncia t�xica i/o que presenta conductes addictives (joc, noves tecnologies, etc.), que deriven en problemes familiars, econ�mics i/o laborals, i de salut.",entornSocial));		
														
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"En tractament i sense afectacions en les activitats essencials per la vida di�ria ",baixaGravetat));		
																	
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,vulnerabilitatRisc,frequenciaGravetat));				
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Sense tractar o No compliment de les pautes de tractament. Afectacions no interfereixen en les activitats essencials de la vida di�ria o les responsabilitats familiars  ",moderadaGravetat));		
																		
		criteriRepository.save(new Criteri ("De 1 a 3 vegades per setmana",continuaFrequencia,riscRisc,frequenciaGravetat));					
		criteriRepository.save(new Criteri ("M�s de 3 vegades per setmana",puntualFrequencia,alttRisc,frequenciaGravetat));		
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Ab�s sever i afectaci� important en les activitats essencials de la vida di�ria o les responsabilitats familiars",altaGravetat));		
																	
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,alttRisc,frequenciaGravetat));				
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Desatenci� institucional  (S.4)","Persones que per la seva situaci� legal o adminsitrativa no tenen acc�s a determinats recursos de l'�mbit de  salut, educaci� i protecci� social.",entornSocial));		
														
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Sense valoraci�",altaGravetat));		
														
		criteriRepository.save(new Criteri ("Capacitat de resoluci� / legalitzaci�",frequentFrequencia,riscRisc,frequenciaGravetat));					
		criteriRepository.save(new Criteri ("Incapacitat de resoluci� ",continuaFrequencia,alttRisc,frequenciaGravetat));		
																
		situacioSocial = situacioSocialRepository.save(new SituacioSocial ("Activitats marginals (S.5)","Treball no legalitzat, activitats marginals (Prostituci�, venda ambulant il�lega, recollida de ferralla, altres).",entornSocial));		
														
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Treball estable, sense coercions ni situacions de risc greu per la persona. Contacte espor�dic amb la policia i/o mesures penals pendent",moderadaGravetat));		
														
		criteriRepository.save(new Criteri ("Sense valoraci�",continuaFrequencia,vulnerabilitatRisc,frequenciaGravetat));					
		criteriRepository.save(new Criteri ("Sense valoraci�",puntualFrequencia,riscRisc,frequenciaGravetat));			
																
		frequenciaGravetat = frequenciaGravetatRepository.save(new FrequenciaGravetat(situacioSocial,"Les activitats marginals comporten un alt risc (salut f�sica i emocianal, delictes penals) per la persona.",altaGravetat));		
																	
		criteriRepository.save(new Criteri ("Sense valoraci�",senseFrequencia,alttRisc,frequenciaGravetat));	

		
		
		// Proteccio
		
		Factor factor;
		
		factor=factorRepository.save(new Factor ("La malaltia causa del d�ficit d'autonomia est� diagnosticada i en tractament"));
		
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornAutonomia));
		
		factor=factorRepository.save(new Factor ("La persona t� reconeguts el grau de discapacitat i / o depend�ncia"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornAutonomia));
		factor=factorRepository.save(new Factor ("La persona �s usu�ria d'un servei, recurs o prestaci�  en funci� de la seva malaltia, discapacitat o situaci� de depend�ncia"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornAutonomia));
		factor=factorRepository.save(new Factor ("Hi ha una xarxa familiar i / o social de suport implicada en la cura"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornAutonomia));
		factor=factorRepository.save(new Factor ("La persona segueix amb regularitat el tractament m�dic i/o t� un h�bits saludables per la seva situaci� de salut"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornAutonomia));

		// Risc
		
		factor=factorRepository.save(new Factor ("Es preveu que el d�ficit d�autonomia de la persona s�agreugi en un curt termini (durant l�any)"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornAutonomia));
		factor=factorRepository.save(new Factor ("La malaltia causa del d�ficit d'autonomia no est� diagnosticada i en tractament"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornAutonomia));
		factor=factorRepository.save(new Factor ("La persona no segueix amb regularitat el tractament m�dic prescrit i/o no t� uns h�bits saludables per la seva situaci� de salut"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornAutonomia));
		factor=factorRepository.save(new Factor ("No existeix una xarxa familiar i / o social que s�impliqui en la cura"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornAutonomia));
		factor=factorRepository.save(new Factor ("Cuidador / a habitual en risc o en situaci� de claudicaci�"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornAutonomia));
		factor=factorRepository.save(new Factor ("La situaci� i/o caracter�stiques del domicili agreugen el d�ficit d�autonomia  de les persones que hi viuen"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornAutonomia));
		factor=factorRepository.save(new Factor ("La persona amb d�ficit d�autonomia t� responsabilitats/carregues familiars"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornAutonomia));
		
		
		// Proteccio
		factor=factorRepository.save(new Factor ("Una o m�s persones de la fam�lia disposa d'ingressos estables (pensions, prestacions, rendes, etc.)."));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEconomic));
		factor=factorRepository.save(new Factor ("Una o m�s persones de la fam�lia disposa d'estalvis"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEconomic));
		factor=factorRepository.save(new Factor ("Una o m�s persones de la fam�lia disposa de patrimoni a part de la primera resid�ncia"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEconomic));
		factor=factorRepository.save(new Factor ("Una o m�s persones de la fam�lia disposa de treball estable."));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEconomic));
		factor=factorRepository.save(new Factor ("Existeix xarxa familiar o relacional disposada a oferir suport econ�mic"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEconomic));
		factor=factorRepository.save(new Factor ("En cas de separaci� o divorci hi ha conveni regulador i es compleixen els seus termes"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEconomic));
		factor=factorRepository.save(new Factor ("La persona t� una bona predisposici� per formar-se i/o treballar"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEconomic));
		factor=factorRepository.save(new Factor ("Es disposa d�un habitatge habitual pel qual no s�ha d�assumir cap despesa o �s un habitatge social"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEconomic));


		// Risc
		factor=factorRepository.save(new Factor ("Existeix un impagament de pensions de manutenci� i la corresponent demanda judicial"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornEconomic));
		factor=factorRepository.save(new Factor ("Existeixen responsabilitats i c�rregues familiars que agreugen la manca de recursos materials/instrumentals"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornEconomic));
		factor=factorRepository.save(new Factor ("Hi ha un endeutament creixent i inassolible amb els recursos disponibles"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornHabitatge));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornEconomic));
		
		
		// Proteccio
		factor=factorRepository.save(new Factor ("La persona mostra una vinculaci� relacional positiva amb la fam�lia extensa o les seves xarxes d�amics i relacions"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("La persona est� vinculada i participa activament amb la comunitat, entitats o associacions del seu entorn"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("Existeix xarxa familiar o relacional disposada a oferir suport i acompanyament"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("En el cas d'inf�ncia que el nen / a mant� relaci� afectiva amb els adults de refer�ncia i iguals"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("En el cas d'inf�ncia que el nen / a mostra una autonomia personal adequada per a la seva edat"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("En el cas d'inf�ncia que el nen / a participa en activitats fora de l'entorn familiar o escolar"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("La persona/fam�lia �s usu�ria de recursos/serveis adre�ats a cobrir la necessitat en l��mbit relacional (escolar, familiar o comunitari) "));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entornFamiliar));


		// Risc
		factor=factorRepository.save(new Factor ("Existeix una xarxa social (ve�ns, amics, fam�lia extensa..) que exerceix una influ�ncia negativa"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("Sospita o malaltia mental diagnosticada que agreugen la situaci� "));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("Existeix una problem�tica de salut no diagnosticada que agreuja la situaci�"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("En el cas d'inf�ncia que el nen / a no mant� relaci� afectiva amb els adults de refer�ncia i iguals"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("En el cas d'inf�ncia que el nen / a no mostra una autonomia personal adequada per a la seva edat"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornFamiliar));
		factor=factorRepository.save(new Factor ("En el cas d'inf�ncia que el nen / a no participa en activitats fora de l'entorn familiar o escolar"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornEscolar));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entornFamiliar));
		
		

		factorEconomicRepository.save(new FactorEconomic("Pagar les factures d'habitatge o serveis p�blics."));
		factorEconomicRepository.save(new FactorEconomic("Mantenir la llar adequadament calenta."));
		factorEconomicRepository.save(new FactorEconomic("Assumir despeses inesperades."));
		factorEconomicRepository.save(new FactorEconomic("Menjar carn o les prote�nes de forma regular."));
		factorEconomicRepository.save(new FactorEconomic("Anar de vacances."));
		factorEconomicRepository.save(new FactorEconomic("Disposar de cotxe."));
		factorEconomicRepository.save(new FactorEconomic("Disposar de rentadora."));
		factorEconomicRepository.save(new FactorEconomic("Disposar de connexi� a internet."));
		factorEconomicRepository.save(new FactorEconomic("Disposar de tel�fon m�bil"));
		
		ambit = ambitRepository.save(new Ambit ("Globalitat del cas"));
		
		Entorn entorn = entornRepository.save (new Entorn ("Globalitat del cas",ambit));
		
		factor=factorRepository.save(new Factor ("La persona i / o fam�lia t� consciencia i accepta  les dificultats / malaltia causants de la seva situaci�"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entorn));
		factor=factorRepository.save(new Factor ("La persona i / o fam�lia accepta el suport i l'orientaci� professional"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entorn));
		factor=factorRepository.save(new Factor ("La persona coneix i t� una actitud proactiva i participativa envers el sistema i les xarxes de protecci�"));
		factorGravetatRepository.save(new FactorGravetat(proteccioGravetat,factor,entorn));
		factor=factorRepository.save(new Factor ("La persona i / o fam�lia no t� consciencia i/o no accepta  les dificultats/malaltia causants de la seva situaci�"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entorn));
		factor=factorRepository.save(new Factor ("La persona i / o fam�lia no accepta el suport i l'orientaci� professional"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entorn));
		factor=factorRepository.save(new Factor ("La persona no t� una actitud activa i ni participativa envers el sistema i les xarxes de protecci� i/o t� una posicionament victimista respecte els sistemes de protecci�"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entorn));
		factor=factorRepository.save(new Factor ("Composici� familiar vulnerable (monoparental, fam�lies nombroses de categoria especial)"));
		factorGravetatRepository.save(new FactorGravetat(riscGravetat,factor,entorn));
		
		
		if (addTest) {
			test();
		}
		
	}
	
	
	private void test () {
		
		log.info ("===========    ADD TEST    ==========");
		
		personaRepository.save(new Persona ("FULANO","APE1","APE2"));
	}
}
