package io.todak.study.springbootmaster.reactive6;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;


@WebFluxTest(controllers = HypermediaItemController.class)
@AutoConfigureRestDocs
public class HypermediaItemControllerDocumentationTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    InventoryService service;

    @MockBean
    ItemRepository repository;


    @Test
    void findingAllItems() {
        when(repository.findAll())
                .thenReturn(Flux.just(
                        new Item("Alf alarm clock",
                                "nothing I really need", 19.99)));
        when(repository.findById((String) null))
                .thenReturn(Mono.just(
                        new Item("item-1", "Alf alarm clock",
                                "nothing I really need", 19.99)));

        this.webTestClient.get().uri("/hypermedia/items")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("findAll-hypermedia",
                        preprocessResponse(prettyPrint())));
    }


    void postNewItem() {
        this.webTestClient.post().uri("/hypermedia/items")
                .body(Mono.just(
                        new Item("item-1", "Alf alarm clock",
                                "nothing I really need", 19.99)),
                        Item.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().isEmpty();
    }


    @Test
    void findOneItem() {
        when(repository.findById("item-1")).thenReturn(Mono.just(
                new Item("item-1", "Alf alarm clock", "nothing I really need", 19.99)));

        this.webTestClient.get().uri("/hypermedia/items/item-1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("findOne-hypermedia", preprocessResponse(prettyPrint()),
                        links(
                                linkWithRel("self").description("??? `Item`??? ?????? ?????? ??????"),
                                linkWithRel("item").description("`Item` ?????? ??????"))));
    }


    @Test
    void findProfile() {
        this.webTestClient.get().uri("/hypermedia/items/profile")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("profile",
                        preprocessResponse(prettyPrint())));
    }
}