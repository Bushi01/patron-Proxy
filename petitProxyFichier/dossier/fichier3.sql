/*fonction suppression de compte*/
CREATE OR REPLACE FUNCTION supcompte (numCpte integer)--num du compte client
RETURNS AS
DECLARE
date today := current_date;

tupleCompte compte%ROWTYPE;
--tupleAncCompte AncCompte%ROWTYPE;

tupleClient client%ROWTYPE;
integer codeClient;
tupleAncClient AncClient%ROWTYPE;

BEGIN
/*copie des informations du compte dans la table anccompte*/
	--trouver tuple dans compte
	SELECT * INTO tupleCompte FROM compte WHERE no_compte=numCpte; --selection tuple qui correspond au compte
	IF NOT FOUND THEN
    	RAISE EXCEPTION 'compte % non trouvé', numCompte;
	END IF;
	--mise en variable du codeClient
	SELECT code_client INTO codeClient FROM compte WHERE no_compte = numCpte; 
	--copie des informations du compte
		/*INSERT INTO anccompte (no_compte, date_creation,date_cloture,code_client) VALUES (numCpte, SELECT date_creation FROM compte WHERE no_compte=numCpte, today, SELECT code_client FROM compte WHERE no_compte = numCpte);*/
	INSERT INTO anccompte (no_compte, date_creation,date_cloture,code_client) VALUES (numCpte, tupleCompte.date_creation, today, codeClient);

/*copie des informations du client dans la table ancclient*/
	--trouver tuple dans client
	/*SELECT * INTO tupleClient FROM client WHERE code_client = (SELECT code_client FROM compte WHERE no_compte = numCpte);*/
	SELECT * INTO tupleClient FROM client WHERE code_client = codeClient;
	--copie des informations du client SAUF SI existe déjà
	PERFORM * FROM ancclient WHERE code_client = codeClient;
	IF NOT FOUND THEN -- si le client n existe pas déjà
		INSERT INTO  ancclient (code_client,nom,prenom,date_naissance,adresse,code_postal,ville,telephone,email)VALUES (codeClient,tupleClient.nom,tupleClient.prenom,tupleClient.date_naissance,tupleClient.adresse,tupleClient.code_postal,tupleClient.ville,tupleClient.telephone,tupleClient.email); -- copie du client
	END IF;

/*suppression du compte*/
DELETE * FROM compte WHERE no_compte = numCpte;

/*suppression  des associations*/
DELETE * FROM associer WHERE no_compte = numCpte;

/*suppression du client SAUF SI celui-ci possede un autre compte (si plusieurs tuples dans la table compte pour un même client)*/
PERFORM * FROM compte WHERE code_client=codeClient;
IF NOT FOUND THEN -- si pas d autres compte
	DELETE * FROM client where code_client = codeClient; --suppression du client
END IF;
END
LANGUAGE plpgsql;

