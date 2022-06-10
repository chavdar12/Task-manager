package bg.manager.simpletaskmanager.entity.base;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "BoardMember")
@Table(name = "board_member")
public class BoardMember {

    @EmbeddedId
    private BoardMemberId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("boardId")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("accountId")
    private Account account;

    public BoardMember() {
    }

    public BoardMemberId getId() {
        return id;
    }

    public void setId(BoardMemberId id) {
        this.id = id;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardMember that = (BoardMember) o;
        return Objects.equals(id, that.id) && Objects.equals(board, that.board) && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, board, account);
    }
}
