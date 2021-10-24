SELECT id, code, male, type, location, company, encrypt, balance
FROM(
        SELECT tc.id, tc.code, tc.male, tc.type, tc.location, tc.company, tc.encrypt, sum(ta.balance) balance
        FROM client tc
        INNER JOIN account ta
        on tc.id = ta.client_id
        WHERE tc.type = IFNULL(:type, tc.type)
        AND tc.location = IFNULL(:location, tc.location)
        GROUP BY tc.id
    ) AS CLIENTE_X_BALANCE
WHERE BALANCE BETWEEN IFNULL(:rangoInicial, balance) AND IFNULL(:rangoFinal, balance)
ORDER BY balance desc, id asc, company asc, male asc