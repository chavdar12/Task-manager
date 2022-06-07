package bg.manager.simpletaskmanager.entity.base;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BoardMemberId implements Serializable {

    @Column(name = "board_id")
    private Integer boardId;

    @Column(name = "account_id")
    private Integer accountId;

    public BoardMemberId(Integer boardId, Integer accountId) {
        this.boardId = boardId;
        this.accountId = accountId;
    }

    public BoardMemberId() {
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardMemberId that = (BoardMemberId) o;
        return Objects.equals(boardId, that.boardId) && Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardId, accountId);
    }
}
