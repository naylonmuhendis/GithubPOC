package app.domain.base.mapper

interface Mapper<in LeftObject, out RightObject> {

    fun mapLeftToRight(obj: LeftObject): RightObject

}