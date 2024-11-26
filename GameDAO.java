package com.chess.dao;

import com.chess.model.Player;
import com.chess.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    public void addPlayer(Player player) throws SQLException {
        String sql = "INSERT INTO Players (name, rating) VALUES (?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, player.getName());
            stmt.setInt(2, player.getRating());
            stmt.executeUpdate();
        }
    }

    public List<Player> getAllPlayers() throws SQLException {
        String sql = "SELECT * FROM Players";
        List<Player> players = new ArrayList<>();
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                players.add(new Player(rs.getInt("id"), rs.getString("name"), rs.getInt("rating")));
            }
        }
        return players;
    }
}
package com.chess.dao;

import com.chess.model.Game;
import com.chess.util.JDBCUtil;

import java.sql.*;

public class GameDAO {
    public int startGame(int player1Id, int player2Id) throws SQLException {
        String sql = "INSERT INTO Games (player1_id, player2_id) VALUES (?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, player1Id);
            stmt.setInt(2, player2Id);
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            }
        }
        return -1;
    }

    public void endGame(int gameId, int winnerId) throws SQLException {
        String sql = "UPDATE Games SET winner_id = ? WHERE id = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, winnerId);
            stmt.setInt(2, gameId);
            stmt.executeUpdate();
        }
    }
}
package com.chess;

import com.chess.dao.PlayerDAO;
import com.chess.dao.GameDAO;
import com.chess.model.Player;

public class Main {
    public static void main(String[] args) {
        try {
            PlayerDAO playerDAO = new PlayerDAO();
            GameDAO gameDAO = new GameDAO();

            // Add players
            Player player1 = new Player(0, "Alice", 1200);
            Player player2 = new Player(0, "Bob", 1100);
            playerDAO.addPlayer(player1);
            playerDAO.addPlayer(player2);

            // Start a game
            int gameId = gameDAO.startGame(1, 2);
            System.out.println("Game started with ID: " + gameId);

            // End the game
            gameDAO.endGame(gameId, 1); // Alice wins
            System.out.println("Game ended. Winner: Player 1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
