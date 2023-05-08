package org.casadocodigo.store.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class ShoppingItem {
    @Getter
    private Book book;
    // apenas para ficar mais facil de gerar o hashcode e equals
    @Getter
    private Integer bookId;

    public static ShoppingItem zeroed() {
        Book book = new Book();
        book.setPrice(BigDecimal.ZERO);
        return new ShoppingItem(book);
    }

    public ShoppingItem(Book book) {
        this.book = book;
        this.bookId = book.getId();
    }

    public BigDecimal getPrice() {
        return book.getPrice();
    }

    public BigDecimal getTotal(Integer quantity) {
        return getPrice().multiply(new BigDecimal(quantity));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ShoppingItem other = (ShoppingItem) obj;
        if (bookId == null) {
            if (other.bookId != null)
                return false;
        } else if (!bookId.equals(other.bookId))
            return false;
        return true;
    }

}