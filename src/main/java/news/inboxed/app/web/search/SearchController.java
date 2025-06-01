package news.inboxed.app.web.search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/search")
public class SearchController {

  @GetMapping
  public String getSearch(@RequestParam(value = "q", required = false) String query) {
      return "searchView";
  }
  
}
