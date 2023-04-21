package com.bde.flix.controller.Generate;

import com.bde.flix.model.entity.History;

public record HistoryGenerateRecord(String uuid, String acc_uuid, String content_uuid, History.ContentType type) {

}
