package DriveMate.spring.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageInfo {
    private Integer page;
    private Integer size;
    private Integer hasNext;
    private Integer totalElements;
    private Integer totalPages;
}
