package com.example.moviedatasource.ui.common

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.moviedatasource.R

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_section_header)
abstract class HeaderModel : EpoxyModelWithHolder<HeaderModel.HeaderViewHolder>() {

    @EpoxyAttribute
    lateinit var title: String

    @EpoxyAttribute
    lateinit var transitionName: String

    override fun bind(holder: HeaderViewHolder) {
        super.bind(holder)
        holder.title.text = title
    }

    class HeaderViewHolder : KotlinEpoxyHolder() {
        val title by bind<TextView>(R.id.tvSectionHeader)
    }
}

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_moviegrid)
abstract class MovieModel : EpoxyModelWithHolder<MovieModel.MovieViewHolder>() {

    @EpoxyAttribute
    lateinit var posterUrl: String

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var clickListener: View.OnClickListener

    @EpoxyAttribute
    lateinit var transitionName: String

    @EpoxyAttribute
    var movieId: Int? = null

    @EpoxyAttribute
    lateinit var glide: RequestManager

    override fun bind(holder: MovieViewHolder) {
        super.bind(holder)
        glide.load(posterUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply {
                RequestOptions()
                    .placeholder(R.drawable.ic_round_local_movies_24px)
                    .error(R.drawable.ic_round_local_movies_24px)
                    .fallback(R.drawable.ic_round_local_movies_24px)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            }
            .into(holder.poster)
        ViewCompat.setTransitionName(holder.poster, transitionName)
        holder.poster.setOnClickListener(clickListener)
    }

    inner class MovieViewHolder : KotlinEpoxyHolder() {
        val poster by bind<ImageView>(R.id.ivPoster)
    }
}

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.view_info_text)
abstract class InfoTextModel : EpoxyModelWithHolder<InfoTextModel.InfoTextViewHolder>() {

    @EpoxyAttribute
    lateinit var text: String

    override fun bind(holder: InfoTextViewHolder) {
        super.bind(holder)
        holder.textView.text = text
    }

    inner class InfoTextViewHolder : KotlinEpoxyHolder() {
        val textView by bind<TextView>(R.id.tvInfoText)
    }
}

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_movie_search_result)
abstract class MovieSearchResultModel :
    EpoxyModelWithHolder<MovieSearchResultModel.MovieSearchResultHolder>() {

    @EpoxyAttribute
    lateinit var posterUrl: String

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var clickListener: View.OnClickListener

    @EpoxyAttribute
    lateinit var transitionName: String

    @EpoxyAttribute
    var movieId: Int? = null

    @EpoxyAttribute
    lateinit var glide: RequestManager

    @EpoxyAttribute
    lateinit var movieTitle: String

    override fun bind(holder: MovieSearchResultModel.MovieSearchResultHolder) {
        super.bind(holder)
        with(holder) {
            ViewCompat.setTransitionName(poster, transitionName)
            poster.setOnClickListener(clickListener)
            title.text = movieTitle
            glide.load(posterUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply {
                    RequestOptions()
                        .placeholder(R.drawable.ic_round_local_movies_24px)
                        .error(R.drawable.ic_round_local_movies_24px)
                        .fallback(R.drawable.ic_round_local_movies_24px)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                }
                .into(holder.poster)
        }
    }

    override fun unbind(holder: MovieSearchResultHolder) {
        super.unbind(holder)
        glide.clear(holder.poster)
    }

    inner class MovieSearchResultHolder : KotlinEpoxyHolder() {
        val poster by bind<ImageView>(R.id.ivPosterSearchResult)
        val title by bind<TextView>(R.id.tvTitleSearchResult)
    }
}

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.view_loading)
abstract class LoadingModel : EpoxyModelWithHolder<LoadingModel.LoadingHolder>() {

    @EpoxyAttribute
    lateinit var description: String

    override fun bind(holder: LoadingHolder) {
        super.bind(holder)
        holder.description.text = description
    }

    inner class LoadingHolder : KotlinEpoxyHolder() {
        val description by bind<TextView>(R.id.tvLoadingDescription)
    }
}
