package app.presentation.base.adapter

interface RecyclerItem {
    val id: Long?
    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int
}