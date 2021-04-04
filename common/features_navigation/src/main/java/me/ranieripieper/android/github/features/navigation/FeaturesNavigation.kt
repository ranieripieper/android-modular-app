package me.ranieripieper.android.github.features.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.os.bundleOf

class FeaturesNavigation {

    companion object {

        fun startRepositoryModule(context: Context) {
            val intent = Intent().setClassName(
                context,
                "me.ranieripieper.android.github.gitrepository.view.GitRepositoryActivity"
            )
            context.startActivity(intent)
        }

        fun startPullRequestModule(context: Context, owner: String, repository: String) {
            val intent = Intent().setClassName(
                context,
                "me.ranieripieper.android.github.pullrequest.view.PullRequestActivity"
            )
            intent.apply {
                putExtras(bundleOf(BundleArguments.ARG_OWNER to owner))
                putExtras(bundleOf(BundleArguments.ARG_REPOSITORY to repository))
            }
            context.startActivity(intent)
        }

        fun openBrowser(context: Context, url: String) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(browserIntent)
        }
    }

}