package it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.postgres;

import it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.PiattoDao;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Piatto;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Ristorante;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Utente;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PiattoDaoPostgres implements PiattoDao {
    Connection connection;

    public PiattoDaoPostgres(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Piatto> findAll() {
        List<Piatto> piatti = new ArrayList<Piatto>();
        try {
            Statement st = connection.createStatement();
            String query = "select * from piatto";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Piatto piatto = new Piatto();
                piatto.setId(rs.getLong("id"));
                piatto.setNome(rs.getString("nome"));
                piatto.setPrezzo(rs.getDouble("prezzo"));
                //todo
                //piatto.setRistoranti();
                piatti.add(piatto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return piatti;
    }

    @Override
    public Piatto findByPrimaryKey(Long id) {
        Piatto piatto = null;
        String query = "select p.id as p_id, p.nome as p_nome, " +
                "p.prezzo as p_prezzo, r.id as r_id  from " +
                "piatto p, serve s, ristorante r " +
                "where p.id = ? " +
                "   and s.piatto = p.id and s.ristorante = r.id";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                if (piatto == null) {
                    piatto = new Piatto();
                    piatto.setId(rs.getLong("p_id"));
                    piatto.setNome(rs.getString("p_nome"));
                    piatto.setPrezzo(rs.getDouble("p_prezzo"));
                }
                Long ristId = rs.getLong("r_id");
                Ristorante r = DBManager.getInstance().getRistoranteDao().findByPrimaryKey(ristId);
                piatto.addRistorante(r);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return piatto;
    }

    @Override
    public void saveOrUpdate(Piatto piatto) {

    }

    @Override
    public void delete(Piatto piatto) {

    }

}
