package app.data.products.entity

import app.domain.base.mapper.Mapper
import app.domain.products.entity.Beer

class BeerMapper : Mapper<BeerResponse, Beer> {

    override fun mapLeftToRight(obj: BeerResponse): Beer = with(obj) {
        Beer(
            id = id,
            name = name,
            tagline = tagline,
            description = description,
            imageUrl = imageUrl,
            abv = abv
        )
    }

}