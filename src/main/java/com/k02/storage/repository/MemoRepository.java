package com.k02.storage.repository;

import com.k02.storage.model.Memo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class MemoRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 메모 목록 조회
    public List<Memo> findAll() {
        String sql = "SELECT * FROM memo ORDER BY id DESC";
        return jdbcTemplate.query(sql, memoRowMapper());
    }

    // 단일 메모 조회
    public Optional<Memo> findById(Long id) {
        String sql = "SELECT * FROM memo WHERE id = ?";
        try {
            Memo memo = jdbcTemplate.queryForObject(sql, memoRowMapper(), id);
            return Optional.ofNullable(memo);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    // 메모 업데이트
    public void update(Long id, String title, String content, LocalDateTime updatedAt) {
        String sql = "UPDATE memo SET title = ?, content = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, title, content, Timestamp.valueOf(updatedAt), id);
    }

    // 메모 추가 (수정됨: created_at, updated_at 모두 저장)
    public void insert(String title, String content, LocalDateTime createdAt) {
        String sql = "INSERT INTO memo (title, content, created_at, updated_at) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, title, content, Timestamp.valueOf(createdAt), Timestamp.valueOf(createdAt));
    }

    // 메모 삭제
    public void delete(Long id) {
        String sql = "DELETE FROM memo WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // RowMapper
    private RowMapper<Memo> memoRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            Memo memo = new Memo();
            memo.setId(rs.getLong("id"));
            memo.setTitle(rs.getString("title"));
            memo.setContent(rs.getString("content"));
            Timestamp created = rs.getTimestamp("created_at");
            Timestamp updated = rs.getTimestamp("updated_at");
            memo.setCreatedAt(created != null ? created.toLocalDateTime() : null);
            memo.setUpdatedAt(updated != null ? updated.toLocalDateTime() : null);
            return memo;
        };
    }
}
