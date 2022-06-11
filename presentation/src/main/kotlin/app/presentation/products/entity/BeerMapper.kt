package app.presentation.products.entity

import app.domain.base.mapper.Mapper
import app.domain.products.entity.Beer

class BeerMapper : Mapper<Beer, BeerUI> {

    override fun mapLeftToRight(obj: Beer): BeerUI = with(obj) {
        BeerUI(
            id = id,
            name = name,
            tagline = tagline,
            description = description,
            imageUrl = imageUrl,
            abv = abv
        )
    }

}